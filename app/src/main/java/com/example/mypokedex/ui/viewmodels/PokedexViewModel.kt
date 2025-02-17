package com.example.mypokedex.ui.viewmodels

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.repository.PokemonRepository
import com.example.mypokedex.ui.input_events.InputEvents
import com.example.mypokedex.ui.ui_state.UiState
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
    private val pokemonRepository: PokemonRepository
): ViewModel() {

    private lateinit var sharedPrefs : SharedPreferences

    fun setSharedPrefs(preferences: SharedPreferences) {
        this.sharedPrefs = preferences
    }

    private val splashShowFlow = MutableStateFlow(true)
    val isSplashShow = splashShowFlow.asStateFlow()


    sealed class ViewActions{
        data object Empty : ViewActions()
        data object Initial : ViewActions()
        data object IsFirstTime : ViewActions()
        data class OnPopulateList (val pokeList: List<Pokemon>) : ViewActions()
    }

    //private val _viewState = MutableStateFlow<UiState<List<Pokemon>>>(UiState.Loading)
    private val _viewState = MutableStateFlow<ViewActions>(ViewActions.Empty)
    val viewState = _viewState.asStateFlow()

    fun onInputEvent(inputEvents: InputEvents) {
        when(inputEvents) {
            InputEvents.OnCallApi -> {
                viewModelScope.launch(Dispatchers.IO) {

                    when(val response = pokemonRepository.getAllPokemon()) {
                        is Resource.Success -> {
                            response.data?.results?.let {
                                _viewState.value = ViewActions.OnPopulateList(response.data.results)
                            }

                        }
                        is Resource.Error -> {
                            response.message?.let { Log.e("MyPokemonFailure", it) }
                        }
                    }

                    splashShowFlow.value = false

                }

            }

            InputEvents.CheckForFirstTime -> {
                if(!sharedPrefs.getBoolean(Constants.SharedKeys.IS_FIRST_OPEN, true)){
                    _viewState.value = ViewActions.Initial
                    return
                }
                _viewState.value = ViewActions.IsFirstTime
                splashShowFlow.value = false
            }

            InputEvents.OnFirstTimeFinish -> {
                sharedPrefs.edit().putBoolean(Constants.SharedKeys.IS_FIRST_OPEN, false).commit()
                _viewState.value = ViewActions.Initial

            }


        }
    }
}