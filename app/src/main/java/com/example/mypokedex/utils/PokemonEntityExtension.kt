package com.example.mypokedex.utils

import com.example.mypokedex.data.local.database.model.PokemonEntity
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.model.Type

fun PokemonEntity.toPokemon() = Pokemon(
    name = this.name,
    id = this.id,
    types = this.types.takeIf { it.isNotEmpty() } ?: listOf(Type("Unknown", "")),
    weight = this.weight,
    height = this.height,
    sprites = this.sprites,
    statsList = this.statsList,
    evolutions = emptyList(),
    favorite = this.favorite,
)

fun PokemonEntity.displayParameters(): String =
    """
            Name: $name
            ID: $id
            Types: ${types.joinToString(", ")}
            Weight: $weight
            Height: $height
            Sprites: ${sprites.entries.joinToString(", ") { "${it.key}: ${it.value}" }}
            Stats List: ${statsList.joinToString(", ")}
            Favorite: $favorite
            Is Loaded: $isLoaded
        """.trimIndent()
