package com.example.mypokedex.data.remote.source

import android.util.Log
import com.example.mypokedex.data.remote.api.PokeApi
import com.example.mypokedex.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import javax.inject.Inject

class PokemonDataSource @Inject constructor(
    private val pokeApi: PokeApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : IPokemonDataSource {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, thrwoable ->
        Log.d("thrwoable.printStackTrace()", thrwoable.stackTraceToString())
    }

    override suspend fun getAllPokemon() =
            pokeApi.getAllPokemon()


    override suspend fun getPokemonByName(name: String) =
            pokeApi.getPokemonByName(name)


    override suspend fun getEvolutionChain(id: String) =
            pokeApi.getEvolutionChain(id)


    override suspend fun getSpecies(name: String) =
            pokeApi.getSpecies(name)

    override suspend fun getTypes() =
        CoroutineScope(ioDispatcher + coroutineExceptionHandler).async {
            pokeApi.getTypes()
        }.await()
}