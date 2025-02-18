package com.example.mypokedex.ui.composables.pokedex

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.mypokedex.R
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.utils.Constants

@Composable
fun PokemonListItem(pokemon: Pokemon, fakeId: Int) {
    Card(
        modifier = Modifier.size(75.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                pokemon.name.replaceFirstChar { it.uppercase() },
                Modifier.padding(20.dp),
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(10.dp))

            AsyncImage(
                model = Constants.Network.retrieveImageUrl(fakeId),
                placeholder = painterResource(R.drawable.gengar),
                contentDescription = null,
                error = painterResource(R.drawable.gengar),
                modifier = Modifier.size(100.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonList() {
    val pokemon = Pokemon("Buba", "lixo")

    PokemonListItem(pokemon, 1)
}