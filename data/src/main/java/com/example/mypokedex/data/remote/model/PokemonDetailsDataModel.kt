package com.example.mypokedex.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonDetails(
    val name: String,
    val order: Int,
    @SerializedName("types")
    val pokemonDetailsTypeResponses: List<PokemonDetailsTypeResponse>,
    val weight: Int,
    val height: Int,
    val sprites: Sprites,
    @SerializedName("stats")
    val statsResponseList: List<StatsResponse>,
    val id: Int
)

data class StatsResponse(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("stat")
    val statType: StatType
)

data class StatType(
    val name: String
)

data class ShowdownSprites(
    @SerializedName("front_default")
    val frontDefault: String
)

data class Sprites(
    val other: OtherSprites,
    val versions: SpriteVersions? = null,
    @SerializedName("front_default")
    val standardFrontDefault: String
)

data class SpriteVersions(
    @SerializedName("generation-v")
    val generationV: GenerationFiveSprites
)

data class GenerationFiveSprites(
    @SerializedName("black-white")
    val blackWhite: BlackWhiteSprites
)

data class BlackWhiteSprites(
    @SerializedName("animated")
    val animatedSprite: AnimatedSprite
)

data class AnimatedSprite(
    @SerializedName("front_default")
    val animatedFrontDefault: String
)

data class PokemonDetailsTypeResponse(
    val slot: Int,
    val type: TypeDetail
)

data class TypeDetail(
    val name: String,
    val url: String,
)

data class OtherSprites(
    val showdown: ShowdownSprites
)


