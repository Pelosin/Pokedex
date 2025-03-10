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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
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

    val searchQuery = MutableStateFlow("")

    private val filterType = MutableStateFlow<FilterType>(FilterType.Empty)

    @OptIn(ExperimentalCoroutinesApi::class)
    val pokedexState =
        searchQuery.flatMapLatest {
            filterType
        }.flatMapLatest {
            pokemonRepository.getAllPokemons(searchQuery.value, filterType.value)
        }.map { UiState.Success(it) }
            .stateIn(viewModelScope, SharingStarted.Lazily, UiState.Loading)


    fun onInputEvent(inputEvents: InputEvents) {
        when (inputEvents) {
            is InputEvents.SearchQueryInput -> {
                searchQuery.value = inputEvents.query
            }

            is InputEvents.OnFavFilter -> {
                if(inputEvents.isFiltered){
                    filterType.value = FilterType.Favorites
                } else {
                    filterType.value = FilterType.Empty
                }
            }

            else -> {}
        }
    }


}

sealed class FilterType {
    data object Empty : FilterType()
    data object Favorites : FilterType()
}