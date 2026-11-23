package com.example.mypokedex.utils

import com.example.mypokedex.data.remote.model.PokemonDetailsTypeResponse
import com.example.mypokedex.domain.model.Type

fun PokemonDetailsTypeResponse.toType(): Type =
    Type(
        name = this.type.name,
        sprite = this.type.url.retrieveUrlId().retrieveTypeImageUrl()
    )
