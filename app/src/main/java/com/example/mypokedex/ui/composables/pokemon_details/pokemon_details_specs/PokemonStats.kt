package com.example.mypokedex.ui.composables.pokemon_details.pokemon_details_specs

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.example.mypokedex.domain.model.Stats

@Composable
fun PokemonStats(
    statsList : List<Stats>
) {
    LazyColumn (
    ) {
        items(statsList.size) {
            val stat = statsList[it]
            Text("${stat.statType.name} -> ${stat.baseStat}", fontSize = 14.sp)
        }
    }
}