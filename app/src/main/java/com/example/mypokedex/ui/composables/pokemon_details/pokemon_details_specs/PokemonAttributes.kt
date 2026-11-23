package com.example.mypokedex.ui.composables.pokemon_details.pokemon_details_specs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.ui.theme.LcdDark
import com.example.mypokedex.ui.theme.LcdGreen
import com.example.mypokedex.ui.theme.LcdPale
import com.example.mypokedex.ui.theme.RetroInk

@Composable
fun PokemonAttributes(
    pokemonDetails: Pokemon
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            "TYPE",
            style = MaterialTheme.typography.labelLarge,
            color = RetroInk
        )

        LazyRow(
            modifier = Modifier.padding(top = 4.dp)
        ) {
            items(pokemonDetails.types.size) {
                PokemonTypeImage(pokemonDetails.types[it].sprite)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            "STATS",
            style = MaterialTheme.typography.labelLarge,
            color = RetroInk
        )

        PokemonStats(pokemonDetails.statsList)

        Spacer(modifier = Modifier.height(12.dp))

        // LCD data screen: weight & height like a device readout
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LcdReadout(
                label = "WEIGHT",
                value = "${pokemonDetails.weight / 10} KG",
                modifier = Modifier.weight(1f)
            )
            LcdReadout(
                label = "HEIGHT",
                value = "${pokemonDetails.height * 10} CM",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun LcdReadout(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(2.dp, RetroInk, RoundedCornerShape(4.dp))
            .background(LcdDark, RoundedCornerShape(4.dp))
            .padding(10.dp)
    ) {
        Column {
            Text(
                label,
                style = MaterialTheme.typography.labelMedium,
                color = LcdPale
            )
            Text(
                value,
                style = MaterialTheme.typography.titleMedium,
                color = LcdGreen
            )
        }
    }
}