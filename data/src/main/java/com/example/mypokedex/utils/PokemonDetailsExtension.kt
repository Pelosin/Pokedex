package com.example.mypokedex.utils

import com.example.mypokedex.data.local.database.model.PokemonEntity
import com.example.mypokedex.data.remote.model.PokemonDetails

fun PokemonDetails.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        id = this.id,
        name = this.name,
        types = this.pokemonDetailsTypeResponses.map { it.toType() },
        weight = this.weight,
        height = this.height,
        sprites = this.sprites.toSpritesList(),
        favorite = false,
        isLoaded = true
    )
}