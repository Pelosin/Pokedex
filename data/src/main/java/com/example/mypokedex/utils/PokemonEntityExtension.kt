package com.example.mypokedex.utils

import com.example.mypokedex.data.local.database.model.PokemonEntity
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.model.Type
import com.example.mypokedex.domain.model.FilterType

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

fun List<PokemonEntity>.filterByName(searchQuery: String) : List<PokemonEntity>{
    if(searchQuery.isEmpty())
        return this

    return this.filter { it.name.contains(searchQuery) }
}

fun List<PokemonEntity>.filterByFilterType(filterType: FilterType) : List<PokemonEntity>{
    when(filterType) {
        FilterType.Empty -> {
            return this
        }
        FilterType.Favorites -> {
            return this.filter { it.favorite }
        }
    }
}


