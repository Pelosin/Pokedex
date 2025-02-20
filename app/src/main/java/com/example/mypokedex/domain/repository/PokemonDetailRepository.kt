package com.example.mypokedex.domain.repository

import com.example.mypokedex.domain.model.PokemonDetails
import com.example.mypokedex.utils.Resource

interface PokemonDetailRepository {
    suspend fun getPokemonByName(name : String) : Resource<PokemonDetails>
}