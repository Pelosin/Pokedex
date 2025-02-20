package com.example.mypokedex.ui.composables.pokedex

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.mypokedex.ui.composables.navigation.Screen
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
                Text("Aconteceu Algum error")
            }

            UiState.Loading -> {}
        }
    }

}