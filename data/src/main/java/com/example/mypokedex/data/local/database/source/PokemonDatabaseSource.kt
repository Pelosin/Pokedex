package com.example.mypokedex.data.local.database.source

import com.example.mypokedex.data.local.database.core.PokedexDatabase
import com.example.mypokedex.data.local.database.model.PokemonEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonDatabaseSource @Inject constructor(
    private val pokedexDatabase: PokedexDatabase
) : IPokemonDatabaseSource {
    override fun insertPokemon(vararg pokemon: PokemonEntity) =
        pokedexDatabase.pokemonDao().insertPokemon(*pokemon)


    override fun getPokemonByName(name: String): Flow<PokemonEntity> =
        pokedexDatabase.pokemonDao().getPokemonByName(name)


    override fun getAllPokemons(): Flow<List<PokemonEntity>> =
        pokedexDatabase.pokemonDao().getAllPokemons()


    override fun getFavoritePokemons(): Flow<List<PokemonEntity>> =
        pokedexDatabase.pokemonDao().getFavoritePokemons()


    override fun updatePokemon(pokemon: PokemonEntity) {
        pokedexDatabase.pokemonDao().updatePokemon(pokemon)
    }

    override fun deletePokemon(name: String) =
        pokedexDatabase.pokemonDao().deletePokemon(name)


    override fun deleteAllPokemons() =
        pokedexDatabase.pokemonDao().deleteAllPokemons()

    override fun favoritePokemon(name: String) {
        pokedexDatabase.pokemonDao().favoritePokemonByName(name)
    }

}