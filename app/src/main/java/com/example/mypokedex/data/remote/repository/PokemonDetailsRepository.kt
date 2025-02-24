package com.example.mypokedex.data.remote.repository

import com.example.mypokedex.data.remote.datasource.IPokemonDataSource
import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.domain.model.PokemonDetails
import com.example.mypokedex.domain.repository.IPokemonDetailRepository
import javax.inject.Inject

class PokemonDetailsRepository @Inject constructor(
    private val pokemonDataSource: IPokemonDataSource
) : IPokemonDetailRepository{
    override suspend fun getPokemonByName(name : String): FunctionResponse<PokemonDetails> =
        pokemonDataSource.getPokemonByName(name)

}