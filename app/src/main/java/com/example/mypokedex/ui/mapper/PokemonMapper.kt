package com.example.mypokedex.ui.mapper

import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.model.PokemonDetails
import com.example.mypokedex.domain.model.RightPokemon

class PokemonMapper {
    fun map(
        listPokemonResponse: List<Pokemon>,
        listPokemonDetails: List<PokemonDetails>
    ): List<RightPokemon> {
        val rightPokemonList = mutableListOf<RightPokemon>()

        listPokemonResponse.forEachIndexed { index, pokemon ->
            rightPokemonList.add(
                RightPokemon(
                    pokemon.name,
                    listPokemonDetails[index].types[0].type.name,
                    listPokemonDetails[index].height
                )
            )
        }

        return rightPokemonList.toList()
    }
}