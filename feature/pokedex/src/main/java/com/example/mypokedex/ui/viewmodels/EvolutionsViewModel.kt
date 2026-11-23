package com.example.mypokedex.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.di.IoDispatcher
import com.example.mypokedex.domain.model.Evolution
import com.example.mypokedex.domain.repository.IPokemonRepository
import com.example.mypokedex.ui.utils.InputEvents
import com.example.mypokedex.ui.utils.UiState
import com.example.mypokedex.utils.LogTags
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
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

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, thrwoable ->
        Log.d("thrwoable.printStackTrace()", thrwoable.stackTraceToString())
    }

    fun onInputEvent(inputEvents: InputEvents) {
        when (inputEvents) {
            is InputEvents.OnGetEvolutions -> {
                viewModelScope.launch(ioDispatcher) {
                    runCatching {
                        pokemonRepository.getEvolutionChain(inputEvents.name)
                    }.onSuccess {
                        _viewState.value = UiState.Success(it)
                    }.onFailure {
                        _viewState.value = UiState.Error(it.stackTraceToString())
                    }
                }
            }

            else -> {}
        }
    }
}