package com.example.mypokedex.data.remote.datasource

import com.example.mypokedex.data.remote.model.PokemonResponse
import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.model.PokemonDetails

interface IPokemonDataSource {
    suspend fun getAllPokemon() : FunctionResponse<List<Pokemon>>
    suspend fun getPokemonByName(name : String) : FunctionResponse<PokemonDetails>

}