package com.example.mypokedex.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.mypokedex.domain.model.Pokemon

@Composable
fun PokemonList(
    pokeList: List<Pokemon>,
) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(pokeList.size) {
                val pokemon = pokeList[it]
                Text(pokemon.name, textAlign = TextAlign.Center)
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