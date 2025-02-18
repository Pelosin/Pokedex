package com.example.mypokedex.ui.composables.pokedex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mypokedex.domain.model.Pokemon

@Composable
fun PokemonList(
    pokeList: List<Pokemon>,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        contentAlignment = Alignment.Center) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(pokeList.size) {
                val pokemon = pokeList[it]
                PokemonListItem(pokemon, it + 1)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PokemonListPreview() {
    val pokeList = listOf<Pokemon>(Pokemon("Buba", "a"), Pokemon("Char", "a"))
    PokemonList(pokeList)
}