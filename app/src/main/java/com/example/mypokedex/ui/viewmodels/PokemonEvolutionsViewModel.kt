package com.example.mypokedex.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.repository.IEvolutionsRepository
import com.example.mypokedex.ui.utils.InputEvents
import com.example.mypokedex.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonEvolutionsViewModel @Inject constructor(
    private val evolutionRepository: IEvolutionsRepository
) : ViewModel() {


    private val _viewState = MutableStateFlow<UiState<List<Pokemon>>>(UiState.Loading)
    val viewState = _viewState.asStateFlow()

    fun onInputEvent(inputEvents: InputEvents) {
        when(inputEvents) {
            is InputEvents.OnGetEvolutions -> {
                viewModelScope.launch {
                    evolutionRepository.getEvolutionChain(inputEvents.name)
                }
            }
            else -> {}
        }
    }
}