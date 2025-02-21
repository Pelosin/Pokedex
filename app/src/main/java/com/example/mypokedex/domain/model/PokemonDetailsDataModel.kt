package com.example.mypokedex.domain.model

import com.google.gson.annotations.SerializedName

data class PokemonDetails(
    val name: String,
    val order: Int,
    val types: List<Type>,
    val weight: Int,
    val height : Int,
    val sprites: Sprites,
    @SerializedName("stats")
    val statsList : List<Stats>
)

data class Stats(
    @SerializedName("base_stat")
    val baseStat : Int,
    @SerializedName("stat")
    val statType : StatType
)

data class StatType(
    val name : String
)

data class ShowdownSprites(
    @SerializedName("front_default")
    val frontDefault : String
)

data class Sprites(
    val other : OtherSprites
)

data class Type(
    val slot: Int,
    val type: TypeDetail
)

data class TypeDetail(
    val name: String,
    val url : String,
)

data class OtherSprites(
    val showdown : ShowdownSprites
)


