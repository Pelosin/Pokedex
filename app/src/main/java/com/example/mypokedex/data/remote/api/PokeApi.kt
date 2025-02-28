package com.example.mypokedex.data.remote.api

import EvolutionResponse
import com.example.mypokedex.data.remote.model.PokemonResponse
import com.example.mypokedex.data.remote.model.SpeciesResponse
import com.example.mypokedex.domain.model.PokemonDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {
    @GET("pokemon/?limit=1000")
    suspend fun getAllPokemon(): Response<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): Response<PokemonDetails>

    @GET("evolution-chain/{id}")
    suspend fun getEvolutionChain(
        @Path("id") id: String
    ): Response<EvolutionResponse>

    @GET("pokemon-species/{name}")
    suspend fun getSpecies(
        @Path("name") name: String
    ): Response<SpeciesResponse>

}