package com.example.mypokedex.ui.composables.pokemon_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.gif.GifDecoder
import coil3.request.ImageRequest
import com.example.mypokedex.ui.composables.pokedex_topbar.PokedexTopbar
import com.example.mypokedex.ui.composables.pokedex_topbar.PokemonDetailsTopbar
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

        }
        UiState.Loading -> {}
    }
}