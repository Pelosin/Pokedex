package com.example.mypokedex.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.di.DefaultDispatcher
import com.example.mypokedex.di.IoDispatcher
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.repository.IPokemonDetailRepository
import com.example.mypokedex.domain.repository.IPokemonRepository
import com.example.mypokedex.ui.utils.InputEvents
import com.example.mypokedex.ui.utils.PokedexUiState
import com.example.mypokedex.ui.utils.PokemonDetailsUiState
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
    private val pokemonDetailsRepository: IPokemonDetailRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {


    private val _pokedexState = MutableStateFlow<PokedexUiState>(PokedexUiState.Loading)
    val pokedexState = _pokedexState.asStateFlow()

    private var pokedexList: List<Pokemon> = emptyList()

    val searchQuery = mutableStateOf("")

    fun onInputEvent(inputEvents: InputEvents) {
        when (inputEvents) {
            InputEvents.OnCallApi -> {
                viewModelScope.launch(ioDispatcher) {
                    when (val response = pokemonRepository.getAllPokemon()) {
                        is FunctionResponse.Success -> {
                            persistPokemonResponse(response)
                            fetchPokemonDetails()
                        }

                        is FunctionResponse.Error -> {
                            response.message?.let {
                                Log.e("MyPokemonFailure", it)
                                _pokedexState.value = PokedexUiState.Error(it)
                            }
                        }
                    }
                }
            }

            is InputEvents.SearchQueryInput -> {
                viewModelScope.launch(defaultDispatcher) {
                    searchQuery.value = inputEvents.query
                    try {
                        (_pokedexState.value as PokedexUiState.Success).data.filter {
                            it.first.name.contains(inputEvents.query.lowercase())
                        }
                    } catch (e : ClassCastException){
                        _pokedexState.value =
                            PokedexUiState.Error(e.stackTraceToString())
                    }
                }
            }

            InputEvents.OnAToZSort -> {
               viewModelScope.launch(defaultDispatcher) {
                   try {
                       (_pokedexState.value as PokedexUiState.Success).data.sortedBy {
                           it.first.name
                       }
                   }catch (e: ClassCastException) {
                       _pokedexState.value = PokedexUiState.Error(
                           e.stackTraceToString()
                       )
                   }
               }
            }

            InputEvents.OnZToASort -> {
                try {
                    (_pokedexState.value as PokedexUiState.Success).data.sortedBy {
                        it.first.name
                    }.reversed()
                }catch (e: ClassCastException) {
                    _pokedexState.value = PokedexUiState.Error(
                        e.stackTraceToString()
                    )
                }
            }

            else -> {}
        }
    }

    private fun persistPokemonResponse(response: FunctionResponse<List<Pokemon>>) {
        response.data?.let { list ->
            pokedexList = list
            val pokedexPairList =
                list.map {
                    it to MutableStateFlow<PokemonDetailsUiState>(
                        PokemonDetailsUiState.Loading
                    )
                }
            _pokedexState.value = PokedexUiState.Success(pokedexPairList)
        }
    }

    private fun fetchPokemonDetails() {
        viewModelScope.launch(ioDispatcher) {
            val stateValue = _pokedexState.value as PokedexUiState.Success
            stateValue.data.forEach { pair ->
                when (val response =
                    pokemonDetailsRepository.getPokemonByName(pair.first.name)) {

                    is FunctionResponse.Error -> {
                        _pokedexState.value = PokedexUiState.Error("Details failled")
                    }
                    is FunctionResponse.Success -> {
                        response.data?.let {
                            pair.second.value = PokemonDetailsUiState.Success(response.data)
                        } ?: PokemonDetailsUiState.Error("Empty Details")
                    }
                }
            }
        }
    }
}