package com.example.mypokedex.data.api

import com.example.mypokedex.domain.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokeApi {
    @GET("pokemon/?limit=50")
    suspend fun getAllPokemon() : Response<PokemonResponse>
}