package com.example.mypokedex.data.remote.repository

import com.example.mypokedex.data.remote.datasource.IPokemonDataSource
import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.repository.IPokemonRepository
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonDataSource: IPokemonDataSource

) : IPokemonRepository{
    override suspend fun getAllPokemon() : FunctionResponse<List<Pokemon>>{
        return pokemonDataSource.getAllPokemon()
    }
}