package com.example.mypokedex.domain.repository

import com.example.mypokedex.data.local.database.model.PokemonEntity
import com.example.mypokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {

    fun fetchPokemonList(data: Flow<List<PokemonEntity>>)

    //    fun getAllTypes() : List<Type>
    fun getPokemonByName(name: String): Flow<Pokemon>

    //fun getEvolutionChain(name: String): List<Evolution>
//    suspend fun retrieveSpriteInDetails(nameList: List<String>): List<Evolution>
    fun getAllPokemons(): Flow<List<Pokemon>>

    fun favoritePokemon(name: String)
}