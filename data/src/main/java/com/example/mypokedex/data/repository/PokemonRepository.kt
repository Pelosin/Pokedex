package com.example.mypokedex.data.repository

import android.util.Log
import com.example.mypokedex.data.local.database.model.PokemonEntity
import com.example.mypokedex.data.local.database.source.IPokemonDatabaseSource
import com.example.mypokedex.data.remote.source.IPokemonDataSource
import com.example.mypokedex.di.IoDispatcher
import com.example.mypokedex.domain.model.Evolution
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.repository.IPokemonRepository
import com.example.mypokedex.domain.model.FilterType
import com.example.mypokedex.utils.LogTags
import com.example.mypokedex.utils.displayParameters
import com.example.mypokedex.utils.filterByFilterType
import com.example.mypokedex.utils.filterByName
import com.example.mypokedex.utils.toEvolutionList
import com.example.mypokedex.utils.toPokemon
import com.example.mypokedex.utils.toPokemonEntity
import com.example.mypokedex.utils.toSpritesList
import com.example.mypokedex.utils.toStats
import com.example.mypokedex.utils.toType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonDataSource: IPokemonDataSource,
    private val pokemonDabataseSource: IPokemonDatabaseSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : IPokemonRepository {

    override fun getAllPokemons(searchQuery : String, filterType: FilterType): Flow<List<Pokemon>> {
        Log.i(LogTags.POKEMON_REPOSITORY, "getAllPokemonsCalled")

        return pokemonDabataseSource.getAllPokemons()
            .also { fetchPokemonList(it) }
            .filterNotNull()
            .filter { it.isNotEmpty() }
            .map { it.filterByName(searchQuery) }
            .map { it.filterByFilterType(filterType) }
            .map {
                it.map { pokemonEntity -> pokemonEntity.toPokemon() }
            }
    }


    private fun fetchPokemonList(data: Flow<List<PokemonEntity>>) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, thrwoable ->
            Log.e(LogTags.POKEMON_REPOSITORY, thrwoable.stackTraceToString())
        }
        Log.i(LogTags.POKEMON_REPOSITORY, "fetchPokemonListCalled")
        CoroutineScope(ioDispatcher + coroutineExceptionHandler).launch {
            data.collectLatest { pokemonEntityList ->
                if (pokemonEntityList.isEmpty()) {
                    loadListRemotely()
                }

                if (pokemonEntityList.isNotEmpty()) {
                    val count = pokemonEntityList.filter { !it.isLoaded }.size
                    Log.d(LogTags.POKEMON_REPOSITORY, "loadUnLoadedPokemonDetail: $count")
                    loadUnloadedPokemons(pokemonEntityList)
                }
            }
        }
    }

    override suspend fun favoritePokemon(name: String) {
        pokemonDabataseSource.favoritePokemon(name)
    }

//    override fun getAllTypes(): List<Type> {
//        //TODO()
//    }

    private suspend fun loadUnloadedPokemons(data: List<PokemonEntity>) {
        data.filter { !it.isLoaded }
            .forEach { pokemonEntity ->
                Log.d(LogTags.POKEMON_REPOSITORY, "unloadedPokemon: ${pokemonEntity.name}")

                val details = pokemonDataSource.getPokemonByName(pokemonEntity.name)

                pokemonEntity.copy(
                    name = details.name,
                    id = details.order,
                    types = details.pokemonDetailsTypeResponses.map { it.toType() },
                    statsList = details.statsResponseList
                        .map { it.toStats() },
                    weight = details.weight,
                    height = details.height,
                    sprites = details.sprites.toSpritesList(),
                    favorite = pokemonEntity.favorite,
                    isLoaded = true,
                )
                    .let {
                        pokemonDabataseSource.updatePokemon(it)
                        Log.d(
                            LogTags.POKEMON_REPOSITORY,
                            "loaded: ${it.name}"
                        )
                    }

            }
    }

    private suspend fun loadListRemotely() {
        Log.i(LogTags.POKEMON_REPOSITORY, "loadingListRemote")
        pokemonDataSource.getAllPokemon()
            .results
            .map { it.toPokemonEntity() }
            .let { pokemonDabataseSource.insertPokemon(*it.toTypedArray()) }
    }

    override fun getPokemonByName(name: String): Flow<Pokemon> {
        return pokemonDabataseSource.getPokemonByName(name)
            .also { fetchPokemonDetail(it, name) }
            .map { it.toPokemon() }
    }

    private fun fetchPokemonDetail(data: Flow<PokemonEntity?>, name: String) {
        CoroutineScope(ioDispatcher).launch {
            data.collectLatest { pokemonEntity ->
                if (pokemonEntity == null) {
                    fetchPokemonDetailRemote(name)
                }
            }
        }
    }

    private suspend fun fetchPokemonDetailRemote(name: String) {
        val pokemonDetails = pokemonDataSource.getPokemonByName(name)
        pokemonDabataseSource.insertPokemon(pokemonDetails.toPokemonEntity())
    }


 //override fun getAllTypes(): List<Type> =
 //    pokemonDataSource.getTypes().types.map { it.toType() }


   override suspend fun getEvolutionChain(name: String): List<Evolution> {
       var evolutionUrl = ""

       val speciesResponse = pokemonDataSource.getSpecies(name)
       evolutionUrl = speciesResponse.speciesEvolutionUrl.url

       val urlId = evolutionUrl.removeSuffix("/").split("/").lastOrNull()

       if (urlId.isNullOrEmpty())
           return emptyList()

       val evolutionResponse = pokemonDataSource.getEvolutionChain(urlId)

       return evolutionResponse.chain.toEvolutionList()

   }

   override suspend fun retrieveSpriteInDetails(nameList: List<String>): List<Evolution> {
       val evolutions = ArrayList<Evolution>()
       nameList.forEach { name ->
           val pokemonDetailsResponse = pokemonDataSource.getPokemonByName(name)

           evolutions.add(
               Evolution(
                   pokemonDetailsResponse.name,
                   pokemonDetailsResponse.sprites.other.showdown.frontDefault
               )
           )

           return emptyList()

       }
       return evolutions
   }
}