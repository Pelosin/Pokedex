package com.example.mypokedex.data.remote.model

import com.example.mypokedex.domain.model.Pokemon

data class PokemonResponse(
    val count: Int,
    val results: List<Pokemon>
)
