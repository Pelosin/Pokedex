package com.example.mypokedex.data.local.database.source

import com.example.mypokedex.data.local.database.model.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface IPokemonDatabaseSource {
    fun insertPokemon(vararg pokemon: PokemonEntity)

    fun getPokemonByName(name: String): Flow<PokemonEntity>

    fun getAllPokemons(): Flow<List<PokemonEntity>>

    fun getFavoritePokemons(): Flow<List<PokemonEntity>>

    fun updatePokemon(pokemon: PokemonEntity)

    fun deletePokemon(name: String)

    fun deleteAllPokemons()

    fun favoritePokemon(name: String)
}