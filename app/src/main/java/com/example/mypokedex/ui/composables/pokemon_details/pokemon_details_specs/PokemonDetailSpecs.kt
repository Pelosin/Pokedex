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
import com.example.mypokedex.domain.model.StatType
import com.example.mypokedex.domain.model.Stats
import com.example.mypokedex.domain.model.Type
import com.example.mypokedex.domain.model.TypeDetail

@Composable
fun PokemonDetailSpecs(
    types: List<Type>,
    stats: List<Stats>,
    weight: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                colorResource(R.color.off_white),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            .padding(top = 60.dp, start = 40.dp),
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
                items(types.size) {
                    PokemonTypeImage(types[it].type)
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
            PokemonStats(stats)
        }

        Text(
            "Weight: ",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 5.dp, top = 15.dp)
        )

        Text("$weight g", fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailsSpecPreview() {
    PokemonDetailSpecs(
        listOf(Type(1, TypeDetail("Grass", "a"))),
        listOf(Stats(65, StatType("Hp"))),
        104
    )
}

