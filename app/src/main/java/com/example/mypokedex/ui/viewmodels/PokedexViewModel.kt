package com.example.mypokedex.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.di.DefaultDispatcher
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.repository.IPokemonRepository
import com.example.mypokedex.ui.notifications.NotificationsManager
import com.example.mypokedex.ui.utils.InputEvents
import com.example.mypokedex.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val pokemonRepository: IPokemonRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    private val notificationsManager: NotificationsManager
) : ViewModel() {

    val pokedexState =
        pokemonRepository.getAllPokemons().map { UiState.Success(it) }
            .stateIn(viewModelScope, SharingStarted.Lazily, UiState.Loading)

    private var pokedexList: List<Pokemon> = mutableListOf()

    val searchQuery = mutableStateOf("")

    fun onInputEvent(inputEvents: InputEvents) {
        when (inputEvents) {
            InputEvents.OnCallApi -> {}

            is InputEvents.SearchQueryInput -> {
                viewModelScope.launch(defaultDispatcher) {
                    searchQuery.value = inputEvents.query
                }
            }

            InputEvents.OnAToZSort -> {
                viewModelScope.launch(defaultDispatcher) {
                    try {

                    } catch (e: ClassCastException) {

                    }
                }
            }

            InputEvents.OnZToASort -> {
                try {

                } catch (e: ClassCastException) {

                }
            }

            InputEvents.OnFavFilter -> {}

            else -> {}
        }
    }


}