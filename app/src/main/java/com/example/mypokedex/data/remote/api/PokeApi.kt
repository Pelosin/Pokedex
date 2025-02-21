package com.example.mypokedex.data.api

import com.example.mypokedex.domain.model.PokemonDetails
import com.example.mypokedex.domain.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {
    @GET("pokemon/?limit=20")
    suspend fun getAllPokemon() : Response<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name : String
    ) : Response<PokemonDetails>
}