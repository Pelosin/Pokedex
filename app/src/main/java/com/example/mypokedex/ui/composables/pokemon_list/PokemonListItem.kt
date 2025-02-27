package com.example.mypokedex.ui.composables.pokemon_list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mypokedex.domain.model.Pokemon

@Composable
fun PokemonListItem(
    pokemon: Pokemon,
    fakeId: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.size(75.dp),
        onClick = { onClick() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                pokemon.name.replaceFirstChar { it.uppercase() },
                Modifier
                    .padding(20.dp)
                    .weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 10.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            PokedexCardList(fakeId)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonList() {
    val pokemon = Pokemon("Buba", "")

    PokemonListItem(pokemon, "1") {}
}