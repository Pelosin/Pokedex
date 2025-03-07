package com.example.mypokedex.domain.model

data class Pokemon(
    val name: String,
    val id: Int = 0,
    val types: List<Type> = listOf(Type("Unknown", "")),
    val weight: Int = 0,
    val height: Int = 0,
    val sprites: Map<String, String> = emptyMap<String, String>(),
    val statsList: List<Stats> = emptyList(),
    val evolutions: List<Evolution> = emptyList(),
    val favorite: Boolean = false
)
