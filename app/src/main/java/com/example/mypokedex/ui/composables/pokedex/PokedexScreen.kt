package com.example.mypokedex.ui.composables.pokedex

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mypokedex.ui.composables.PokemonList
import com.example.mypokedex.ui.viewmodels.PokedexViewModel
import com.example.mypokedex.utils.input_events.InputEvents
import com.example.mypokedex.utils.ui_state.UiState

@Composable
fun PokedexScreen(pokedexViewModel: PokedexViewModel) {
    pokedexViewModel.onInputEvent(InputEvents.OnCallApi)
    val myAction = pokedexViewModel.viewState.collectAsStateWithLifecycle().value

    when(myAction) {
        is UiState.Success -> {
            myAction.data?.let { PokemonList(it) }
        }
        is UiState.Error -> {
            Text("Aconteceu Algum error")
        }
        UiState.Loading -> {}
    }
}