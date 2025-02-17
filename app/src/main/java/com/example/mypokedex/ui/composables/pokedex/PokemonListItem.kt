package com.example.mypokedex.ui.composables.pokedex

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mypokedex.domain.model.Pokemon

@Composable
fun PokemonListItem(pokemon: Pokemon) {
    Card(modifier = Modifier
        .size(200.dp)
    ) {
        Column {
            Text(pokemon.name, Modifier.padding(20.dp))
            Spacer(Modifier.height(10.dp))
            Text()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonList() {
    val pokemon = Pokemon("Buba", "lixo")

    PokemonListItem(pokemon)
}