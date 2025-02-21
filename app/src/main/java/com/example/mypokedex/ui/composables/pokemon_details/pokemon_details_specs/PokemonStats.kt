package com.example.mypokedex.ui.composables.pokemon_details.pokemon_details_specs

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mypokedex.domain.model.StatType
import com.example.mypokedex.domain.model.Stats

@Composable
fun PokemonStats(
    statsList : List<Stats>
) {
    LazyColumn (
        modifier = Modifier.padding(start = 10.dp)
    ) {
        items(statsList.size) {
            val stat = statsList[it]
            Text("${stat.statType.name} -> ${stat.baseStat}", fontSize = 14.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonStatsPreview() {
    PokemonStats(
        listOf(
            Stats(60, StatType("hp")),
            Stats(60, StatType("hp")),
            Stats(60, StatType("hp")),
        )
    )
}