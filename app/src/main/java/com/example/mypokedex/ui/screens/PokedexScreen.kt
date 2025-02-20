package com.example.mypokedex.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.mypokedex.ui.composables.pokemon_list.PokemonList
import com.example.mypokedex.ui.composables.pokedex_topbar.PokedexTopbar
import com.example.mypokedex.ui.viewmodels.PokedexViewModel
import com.example.mypokedex.utils.input_events.InputEvents
import com.example.mypokedex.utils.ui_state.UiState

@ExperimentalMaterial3Api
@Composable
fun PokedexScreen(
    pokedexViewModel: PokedexViewModel,
    navController: NavController
) {

    Scaffold(
        topBar = { PokedexTopbar(Modifier) }
    ) { innerPadding ->
        pokedexViewModel.onInputEvent(InputEvents.OnCallApi)
        val myAction = pokedexViewModel.viewState.collectAsStateWithLifecycle().value

        when (myAction) {
            is UiState.Success -> {
                myAction.data?.let {
                    PokemonList(
                        it,
                        innerPadding,
                        navController
                    )
                }
            }

            is UiState.Error -> {
                PokemonErrorScreen(myAction.message)
            }

            UiState.Loading -> {}
        }
    }

}