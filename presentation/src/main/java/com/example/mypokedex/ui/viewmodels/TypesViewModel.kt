package com.example.mypokedex.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.di.IoDispatcher
import com.example.mypokedex.domain.model.Type
import com.example.mypokedex.domain.repository.IPokemonRepository
import com.example.mypokedex.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TypesViewModel @Inject constructor(
    private val pokemonRepository: IPokemonRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _typesState = MutableStateFlow<UiState<List<Type>>>(UiState.Loading)
    val typeState = _typesState.asStateFlow()

    init {
        viewModelScope.launch {

        }
    }

}