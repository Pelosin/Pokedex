package com.example.mypokedex.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mypokedex.ui.composables.pokemon_list.PokemonList
import com.example.mypokedex.ui.composables.pokedex_topbar.PokedexTopbar
import com.example.mypokedex.ui.viewmodels.PokedexViewModel
import com.example.mypokedex.ui.utils.InputEvents
import com.example.mypokedex.ui.utils.UiState

@ExperimentalMaterial3Api
@Composable
fun PokedexScreen(
    navController: NavController
) {

    val pokedexViewModel: PokedexViewModel = hiltViewModel()

    Scaffold(
        topBar = { PokedexTopbar(Modifier) }
    ) { innerPadding ->
        pokedexViewModel.onInputEvent(InputEvents.OnCallApi)
        when (val myAction =
            pokedexViewModel.viewState.collectAsStateWithLifecycle().value) {
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