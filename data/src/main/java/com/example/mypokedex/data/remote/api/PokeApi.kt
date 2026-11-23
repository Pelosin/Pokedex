package com.example.mypokedex.data.remote.api

import EvolutionResponse
import com.example.mypokedex.data.remote.model.PokemonDetails
import com.example.mypokedex.data.remote.model.PokemonResponse
import com.example.mypokedex.data.remote.model.SpeciesResponse
import com.example.mypokedex.data.remote.model.TypeListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {
    @GET("pokemon/?limit=300")
    suspend fun getAllPokemon(): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): PokemonDetails

    @GET("evolution-chain/{id}")
    suspend fun getEvolutionChain(
        @Path("id") id: String
    ): EvolutionResponse

    @GET("pokemon-species/{name}")
    suspend fun getSpecies(
        @Path("name") name: String
    ): SpeciesResponse

    @GET("type")
    suspend fun getTypes(): TypeListResponse
}