package com.example.mypokedex.ui.composables.pokemon_details.pokemon_details_specs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mypokedex.domain.model.StatType
import com.example.mypokedex.domain.model.Stats

@Composable
fun PokemonStats(
    statsList: List<Stats>
) {
    LazyColumn(
        modifier = Modifier.padding(start = 10.dp)
    ) {
        items(statsList.size) {
            val stat = statsList[it]

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(end = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LinearProgressIndicator(
                    progress = { stat.baseStat / 100f },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth(0.5f)
                        .height(7.5.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    strokeCap = StrokeCap.Round,
                    gapSize = ((-15).dp),
                    drawStopIndicator = {}
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = stat.statType.name, fontSize = 10.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

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