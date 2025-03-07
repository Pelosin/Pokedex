package com.example.mypokedex.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.di.IoDispatcher
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.repository.IPokemonRepository
import com.example.mypokedex.ui.utils.InputEvents
import com.example.mypokedex.ui.utils.UiState
import com.example.mypokedex.utils.LogTags
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonRepository: IPokemonRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _detailsState = MutableStateFlow<UiState<Pokemon>>(UiState.Loading)
    val detailsState = _detailsState.asStateFlow()

    fun onInputEvent(inputEvents: InputEvents) {
        when (inputEvents) {
            is InputEvents.OnDetailsApiCall -> {
                viewModelScope.launch(ioDispatcher) {
                    val response = pokemonRepository.getPokemonByName(inputEvents.name)
                    Log.d(LogTags.POKEMON_DTVM, response.toString())
                    response.collectLatest {
                        _detailsState.value = UiState.Success(it)
                    }
                }
            }

            is InputEvents.OnFavClick -> {
                viewModelScope.launch(ioDispatcher) {
                    pokemonRepository.favoritePokemon(inputEvents.name)
                }
            }

            else -> {}
        }
    }
}