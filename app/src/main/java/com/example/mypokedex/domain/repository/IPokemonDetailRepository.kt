package com.example.mypokedex.domain.repository

import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.domain.model.PokemonDetails

interface IPokemonDetailRepository {

    suspend fun getPokemonByName(name : String) : FunctionResponse<PokemonDetails>
}