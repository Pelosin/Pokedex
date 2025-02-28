package com.example.mypokedex.ui.composables.pokemon_list

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.ui.composables.navigation.Screen
import com.example.mypokedex.ui.utils.InputEvents
import com.example.mypokedex.ui.utils.PokemonDetailsUiState
import com.example.mypokedex.ui.utils.UiState
import com.example.mypokedex.ui.viewmodels.PokemonDetailsViewModel
import com.example.mypokedex.utils.retrieveUrlId
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun PokemonList(
    pokeList: List<Pair<Pokemon, MutableStateFlow<PokemonDetailsUiState>>>,
    navController: NavController
) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(pokeList.size) {
                val pokemon = pokeList[it]

                PokemonListItem(
                    pokemon,
                    pokemon.first.url.retrieveUrlId()
                ) { navController.navigate(Screen.Details.route + "/${pokemon.first.name}") }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonListPreview() {
    val pokeList = listOf(Pokemon("Buba", "a"), Pokemon("Char", "a"))
    //PokemonList(pokeList, rememberNavController())
}