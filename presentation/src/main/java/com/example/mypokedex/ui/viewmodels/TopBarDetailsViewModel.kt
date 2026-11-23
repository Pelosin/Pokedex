package com.example.mypokedex.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.domain.repository.IPokemonRepository
import com.example.mypokedex.di.IoDispatcher
import com.example.mypokedex.ui.utils.InputEvents
import com.example.mypokedex.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopBarDetailsViewModel @Inject constructor(
    private val pokemonRepository: IPokemonRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _detailsState = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val detailsState = _detailsState.asStateFlow()

    fun onInputEvent(inputEvents: InputEvents) {
        when (inputEvents) {
            is InputEvents.OnFavClick -> {
                viewModelScope.launch(ioDispatcher) {
                    pokemonRepository.favoritePokemon(inputEvents.name)
                }
            }

            else -> {}
        }
    }
}