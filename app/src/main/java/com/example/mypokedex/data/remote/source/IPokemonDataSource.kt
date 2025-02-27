package com.example.mypokedex.data.remote.source

import EvolutionResponse
import com.example.mypokedex.data.remote.model.SpeciesResponse
import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.model.PokemonDetails

interface IPokemonDataSource {
    suspend fun getAllPokemon(): FunctionResponse<List<Pokemon>>
    suspend fun getPokemonByName(name: String): FunctionResponse<PokemonDetails>
    suspend fun getEvolutionChain(id: String): FunctionResponse<EvolutionResponse>
    suspend fun getSpecies(name: String): FunctionResponse<SpeciesResponse>
}