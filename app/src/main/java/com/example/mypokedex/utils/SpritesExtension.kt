package com.example.mypokedex.utils

import com.example.mypokedex.data.remote.model.Sprites

fun Sprites.toSpritesList(): Map<String, String> =
    mapOf(
        Pair(
            "gif",
            this.other.showdown.frontDefault
        ),
        Pair(
            "default",
            this.standardFrontDefault
        ),
        Pair(
            "pixelAnimated",
            this.versions?.generationV?.blackWhite?.animatedSprite?.animatedFrontDefault ?: ""
        )
    )
