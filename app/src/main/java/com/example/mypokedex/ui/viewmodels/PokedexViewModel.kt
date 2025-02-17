package com.example.mypokedex.ui.viewmodels

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.repository.PokemonRepository
import com.example.mypokedex.utils.input_events.InputEvents
import com.example.mypokedex.utils.ui_state.UiState
import com.example.mypokedex.utils.Constants
import com.example.mypokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val sharedPrefs: SharedPreferences
): ViewModel() {

    //private lateinit var sharedPrefs : SharedPreferences

    private val _viewState = MutableStateFlow<UiState<List<Pokemon>>>(UiState.Loading)
    val viewState = _viewState.asStateFlow()

    fun onInputEvent(inputEvents: InputEvents) {
        when(inputEvents) {
            InputEvents.OnCallApi -> {
                viewModelScope.launch(Dispatchers.IO) {
                    when(val response = pokemonRepository.getAllPokemon()) {
                        is Resource.Success -> {
                            response.data?.results?.let {
                                _viewState.value = UiState.Success(response.data.results)
                            }

                        }
                        is Resource.Error -> {
                            response.message?.let { Log.e("MyPokemonFailure", it) }
                        }
                    }
                }
            }

            InputEvents.OnFirstTimeFinish -> {
                sharedPrefs.edit().putBoolean(Constants.SharedKeys.IS_FIRST_OPEN, false).apply()
                _viewState.value = UiState.Success(null)
            }
        }
    }
}