package com.example.mypokedex.ui.composables.pokedex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.ui.composables.navigation.Screen

@Composable
fun PokemonList(
    pokeList: List<Pokemon>,
    innerPadding : PaddingValues,
    navController: NavController
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .padding(10.dp),
        contentAlignment = Alignment.Center) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(pokeList.size) {
                val pokemon = pokeList[it]
                PokemonListItem(pokemon, it + 1, {navController.navigate(Screen.Details.route + "/${pokemon.name}")})
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PokemonListPreview() {
    val pokeList = listOf<Pokemon>(Pokemon("Buba", "a"), Pokemon("Char", "a"))
    PokemonList(pokeList, PaddingValues(), NavController(LocalContext.current))
}