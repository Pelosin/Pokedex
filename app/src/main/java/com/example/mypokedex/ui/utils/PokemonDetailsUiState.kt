package com.example.mypokedex.ui.utils

import com.example.mypokedex.data.remote.model.PokemonDetails

sealed class PokemonDetailsUiState {
    data object Loading : PokemonDetailsUiState()
    data class Success(val data: PokemonDetails) : PokemonDetailsUiState()
    data class Error(val message: String) : PokemonDetailsUiState()

}