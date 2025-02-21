package com.example.mypokedex.domain.repository

import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.domain.model.Pokemon

interface IPokemonRepository {

    suspend fun getAllPokemon() : FunctionResponse<List<Pokemon>>
}