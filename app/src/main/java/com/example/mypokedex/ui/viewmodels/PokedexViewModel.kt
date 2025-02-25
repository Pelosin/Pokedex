package com.example.mypokedex.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.di.IoDispatcher
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.repository.IPokemonRepository
import com.example.mypokedex.ui.utils.InputEvents
import com.example.mypokedex.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val pokemonRepository: IPokemonRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {


    private val _viewState = MutableStateFlow<UiState<List<Pokemon>>>(UiState.Loading)
    val viewState = _viewState.asStateFlow()

    fun onInputEvent(inputEvents: InputEvents) {
        when(inputEvents) {
            InputEvents.OnCallApi -> {
                viewModelScope.launch(ioDispatcher) {
                    when(val response = pokemonRepository.getAllPokemon()) {
                        is FunctionResponse.Success -> {
                            response.data?.let {
                                _viewState.value = UiState.Success(response.data)
                            }
                        }
                        is FunctionResponse.Error -> {
                            response.message?.let { Log.e("MyPokemonFailure", it)
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