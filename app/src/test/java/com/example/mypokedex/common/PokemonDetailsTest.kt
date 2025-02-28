package com.example.mypokedex.common

data class PokemonDetailsTest(
    val name: String = "Pikachu",
    val order: Int = 25,
    val types: List<Type> = listOf(
        Type(
            slot = 1,
            type = TypeDetail(name = "Electric", url = "https://pokeapi.co/api/v2/type/electric/")
        )
    ),
    val weight: Int = 60,
    val height: Int = 4,
    val sprites: Sprites = Sprites(
        other = OtherSprites(
            showdown = ShowdownSprites(frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png")
        )
    ),
    val statsList: List<Stats> = listOf(
        Stats(baseStat = 35, statType = StatType(name = "hp")),
        Stats(baseStat = 55, statType = StatType(name = "attack")),
        Stats(baseStat = 40, statType = StatType(name = "defense")),
        Stats(baseStat = 50, statType = StatType(name = "special-attack")),
        Stats(baseStat = 50, statType = StatType(name = "special-defense")),
        Stats(baseStat = 90, statType = StatType(name = "speed"))
    )
)

data class Stats(
    val baseStat: Int = 50,
    val statType: StatType = StatType(name = "hp")
)

data class StatType(
    val name: String = "hp"
)

data class ShowdownSprites(
    val frontDefault: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png"
)

data class Sprites(
    val other: OtherSprites = OtherSprites(
        showdown = ShowdownSprites()
    )
)

data class Type(
    val slot: Int = 1,
    val type: TypeDetail = TypeDetail(
        name = "Electric",
        url = "https://pokeapi.co/api/v2/type/electric/"
    )
)

data class TypeDetail(
    val name: String = "Electric",
    val url: String = "https://pokeapi.co/api/v2/type/electric/"
)

data class OtherSprites(
    val showdown: ShowdownSprites = ShowdownSprites()
)

