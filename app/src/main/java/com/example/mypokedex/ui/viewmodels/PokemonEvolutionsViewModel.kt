package com.example.mypokedex.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.data.remote.repository.EvolutionRepository
import com.example.mypokedex.domain.repository.IEvolutionsRepository
import com.example.mypokedex.domain.repository.IPokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonEvolutionsViewModel @Inject constructor(
    private val evolutionRepository: IEvolutionsRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            evolutionRepository.getEvolutionChain("bulbasaur")
        }
    }
}