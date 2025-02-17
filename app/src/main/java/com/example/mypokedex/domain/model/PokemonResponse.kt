package com.example.mypokedex.domain.model

data class PokemonResponse(
    val count: Int,
    val results : List<Pokemon>
)
