package com.example.mypokedex.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.mypokedex.ui.composables.pokemon_details.PokemonDetails
import com.example.mypokedex.ui.viewmodels.PokemonDetailsViewModel
import com.example.mypokedex.utils.input_events.InputEvents
import com.example.mypokedex.utils.ui_state.UiState

@ExperimentalMaterial3Api
@Composable
fun PokemonDetailScreen(
    name: String,
    pokemonDetailsViewModel: PokemonDetailsViewModel,
    navController: NavController
) {
    pokemonDetailsViewModel.onInputEvent(InputEvents.OnDetailsApiCall(name))

    when (val myState = pokemonDetailsViewModel.viewState.collectAsStateWithLifecycle().value) {
        is UiState.Success -> {
            myState.data?.let {
                PokemonDetails(myState.data, navController)
            }
        }

        is UiState.Error -> {
            PokemonErrorScreen(myState.message)
        }
        UiState.Loading -> {}
    }
}