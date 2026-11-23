package com.example.mypokedex.utils

import com.example.mypokedex.data.local.database.model.PokemonEntity
import com.example.mypokedex.data.remote.model.PokemonListResponse
import com.example.mypokedex.domain.model.Pokemon

fun PokemonListResponse.toPokemon(): Pokemon =
    Pokemon(
        name = this.name,
        sprites = mapOf(Pair("default", this.url.retrieveUrlId().retrieveImageUrl()))
    )

fun PokemonListResponse.toPokemonEntity(): PokemonEntity =
    PokemonEntity(
        name = this.name,
        sprites = mapOf(Pair("default", this.url.retrieveUrlId().retrieveImageUrl()))
    )
