package com.example.mypokedex.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.domain.model.PokemonDetails
import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.domain.repository.IPokemonDetailRepository
import com.example.mypokedex.ui.utils.InputEvents
import com.example.mypokedex.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonDetailRepository: IPokemonDetailRepository
) : ViewModel() {


    private val _viewState = MutableStateFlow<UiState<PokemonDetails>>(UiState.Loading)
    val viewState = _viewState.asStateFlow()

    fun onInputEvent(inputEvents: InputEvents) {
        when(inputEvents) {
            is InputEvents.OnDetailsApiCall -> {
                viewModelScope.launch {
                    when(val response =
                        pokemonDetailRepository.getPokemonByName(inputEvents.name)) {
                        is FunctionResponse.Success -> {
                            response.data?.let {
                                _viewState.value = UiState.Success(it)
                            }
                        }
                        is FunctionResponse.Error -> {
                            response.message?.let { Log.e("MyPokemonDetailsFailure", it)
                                _viewState.value = UiState.Error(response.message)
                            }
                        }
                    }
                }
            }
            else -> {}
        }
    }
}