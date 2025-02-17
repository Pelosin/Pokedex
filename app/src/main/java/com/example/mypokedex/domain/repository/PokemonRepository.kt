package com.example.mypokedex.domain.repository

import com.example.mypokedex.domain.model.PokemonResponse
import com.example.mypokedex.utils.Resource

interface PokemonRepository {
    suspend fun getAllPokemon() : Resource<PokemonResponse>
}