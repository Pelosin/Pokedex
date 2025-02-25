package com.example.mypokedex.ui.composables.pokemon_details.pokemon_details_specs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mypokedex.R
import com.example.mypokedex.domain.model.OtherSprites
import com.example.mypokedex.domain.model.PokemonDetails
import com.example.mypokedex.domain.model.ShowdownSprites
import com.example.mypokedex.domain.model.Sprites

@Composable
fun PokemonAttributes(
    pokemonDetails: PokemonDetails
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                colorResource(R.color.white),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            .padding(start = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Type: ",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )

            LazyRow(
            ) {
                items(pokemonDetails.types.size) {
                    PokemonTypeImage(pokemonDetails.types[it].type)
                }
            }
        }

        Text(
            "Stats: ",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 5.dp, top = 5.dp)
        )

        Row {
            PokemonStats(pokemonDetails.statsList)
        }

        Text(
            "Weight: ",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 5.dp, top = 15.dp)
        )

        Text(
            "${pokemonDetails.weight / 10} kg",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 10.dp)
        )

        Text(
            "Height: ",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 5.dp, top = 15.dp)
        )

        Text(
            "${(pokemonDetails.height.toDouble() * 10)} cm",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 10.dp)
        )
    }
}

@Preview
@Composable
fun PreviewPokemonAttributes() {
    PokemonAttributes(
        PokemonDetails(
            "Buba",
            1,
            emptyList(),
            1,
            2,
            Sprites(OtherSprites(ShowdownSprites("a"))),
            emptyList()
        )
    )
}