package com.example.mypokedex.ui.utils

import com.example.mypokedex.domain.model.Pokemon

sealed class PokemonDetailsUiState {
    data object Loading : PokemonDetailsUiState()
    data class Success(val data: Pokemon) : PokemonDetailsUiState()
    data class Error(val message: String) : PokemonDetailsUiState()

}