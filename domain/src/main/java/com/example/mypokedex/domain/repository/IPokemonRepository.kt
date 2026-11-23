package com.example.mypokedex.domain.repository

import com.example.mypokedex.domain.model.Evolution
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.model.FilterType
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {

    //    fun getAllTypes() : List<Type>
    fun getPokemonByName(name: String): Flow<Pokemon>

    suspend fun getEvolutionChain(name: String): List<Evolution>
    suspend fun retrieveSpriteInDetails(nameList: List<String>): List<Evolution>
    fun getAllPokemons(searchQuery : String, filterType: FilterType): Flow<List<Pokemon>>

    suspend fun favoritePokemon(name: String)
}