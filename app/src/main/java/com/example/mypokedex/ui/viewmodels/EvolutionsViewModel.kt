package com.example.mypokedex.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.di.IoDispatcher
import com.example.mypokedex.domain.model.Evolution
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
class EvolutionsViewModel @Inject constructor(
    private val pokemonRepository: IPokemonRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {


    private val _viewState = MutableStateFlow<UiState<List<Evolution>>>(UiState.Loading)
    val viewState = _viewState.asStateFlow()

    fun onInputEvent(inputEvents: InputEvents) {
        when (inputEvents) {
            is InputEvents.OnGetEvolutions -> {
                viewModelScope.launch(ioDispatcher) {
//                    when (val state = pokemonRepository.getEvolutionChain(inputEvents.name)) {
//                        is FunctionResponse.Success -> {
//                            _viewState.value = UiState.Success(state.data)
//                        }
//
//                        is FunctionResponse.Error -> {
//                            _viewState.value = UiState.Error("Lista vazia")
//                        }
//                    }

                }
            }

            else -> {}
        }
    }
}