package com.example.mypokedex.domain.model

import com.example.mypokedex.utils.Constants

data class PokemonDetails(
    val name: String,
    val order: Int,
    val types: List<Type>,
    val weight: Int,
    val sprites: Sprites
)

