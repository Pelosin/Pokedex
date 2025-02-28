package com.example.mypokedex.ui.utils

import com.example.mypokedex.domain.model.PokemonDetails

sealed class PokemonDetailsUiState {
    object Loading : PokemonDetailsUiState()
    data class Success(val data: PokemonDetails) : PokemonDetailsUiState()
    data class Error(val message: String) : PokemonDetailsUiState()

}