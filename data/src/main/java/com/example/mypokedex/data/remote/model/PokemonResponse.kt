package com.example.mypokedex.data.remote.model


data class PokemonResponse(
    val count: Int,
    val results: List<PokemonListResponse>
)

data class PokemonListResponse(
    val name: String,
    val url: String
)
