package com.example.mypokedex.data.remote.source

import EvolutionResponse
import com.example.mypokedex.data.remote.model.PokemonDetails
import com.example.mypokedex.data.remote.model.PokemonResponse
import com.example.mypokedex.data.remote.model.SpeciesResponse
import com.example.mypokedex.data.remote.model.TypeListResponse

interface IPokemonDataSource {
    suspend fun getAllPokemon(): PokemonResponse
    suspend fun getPokemonByName(name: String): PokemonDetails
    suspend fun getEvolutionChain(id: String): EvolutionResponse
    suspend fun getSpecies(name: String): SpeciesResponse
    suspend fun getTypes(): TypeListResponse
}