package com.example.mypokedex.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.domain.model.PokemonDetails
import com.example.mypokedex.domain.repository.PokemonDetailRepository
import com.example.mypokedex.utils.Resource
import com.example.mypokedex.utils.input_events.InputEvents
import com.example.mypokedex.utils.ui_state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonDetailRepository: PokemonDetailRepository
) : ViewModel() {


    private val _viewState = MutableStateFlow<UiState<PokemonDetails>>(UiState.Loading)
    val viewState = _viewState.asStateFlow()

    fun onInputEvent(inputEvents: InputEvents) {
        when(inputEvents) {
            is InputEvents.OnDetailsApiCall -> {
                viewModelScope.launch {
                    when(val response =
                        pokemonDetailRepository.getPokemonByName(inputEvents.name)) {
                        is Resource.Success -> {
                            response.data?.let {
                                _viewState.value = UiState.Success(it)
                            }
                        }
                        is Resource.Error -> {
                            response.message?.let { Log.e("MyPokemonDetailsFailure", it)
                                _viewState.value = UiState.Error("Erro doido")
                            }
                        }
                    }
                }
            }
            else -> {}
        }
    }
}