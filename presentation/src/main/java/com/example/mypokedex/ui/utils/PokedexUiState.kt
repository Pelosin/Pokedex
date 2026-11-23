package com.example.mypokedex.ui.utils

import com.example.mypokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow

sealed class PokedexUiState {
    data class Success(
        val data: List<Pair<Pokemon, MutableStateFlow<PokemonDetailsUiState>>>
    ) : PokedexUiState()

    data class Error(val message: String) : PokedexUiState()
    data object Loading : PokedexUiState()
}