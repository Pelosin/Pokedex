package com.example.mypokedex.ui.composables.pokemon_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.ui.composables.navigation.Screen

@Composable
fun PokemonList(
    pokemonList: List<Pokemon>,
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
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(pokemonList.size, key = { pokemonList[it].name }) {
                val pokemon = pokemonList[it]

                PokemonListItem(
                    pokemon,
                    pokemon.sprites["default"]
                ) { navController.navigate(Screen.Details.route + "/${pokemon.name}") }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonListPreview() {
}