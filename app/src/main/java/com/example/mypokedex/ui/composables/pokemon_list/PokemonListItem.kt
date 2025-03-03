package com.example.mypokedex.ui.composables.pokemon_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.ui.utils.PokemonDetailsUiState
import com.example.mypokedex.utils.getTypeColor
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun PokemonListItem(
    pokemonStatePair: Pair<Pokemon, MutableStateFlow<PokemonDetailsUiState>>,
    fakeId: String,
    onClick: () -> Unit
) {
    val myDetailsState = pokemonStatePair.second.collectAsStateWithLifecycle().value

    val colorState = remember { mutableStateOf(Color.Gray) }

    when (myDetailsState) {
        is PokemonDetailsUiState.Success -> {
            colorState.value = myDetailsState.data.types.first().type.name.getTypeColor()
        }

        PokemonDetailsUiState.Loading -> {}
        is PokemonDetailsUiState.Error -> {}
    }

    Card(
        modifier = Modifier
            .size(75.dp)
            .background(color = colorState.value),
        onClick = { onClick() },

        ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                pokemonStatePair.first.name.replaceFirstChar { it.uppercase() },
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

    //PokemonListItem(pokemon, "1") {}
}