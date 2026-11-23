package com.example.mypokedex.ui.composables.pokemon_details.pokemon_details_specs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mypokedex.domain.model.Stats
import com.example.mypokedex.ui.theme.LcdGreen
import com.example.mypokedex.ui.theme.LcdOff
import com.example.mypokedex.ui.theme.RetroInk
import com.example.mypokedex.ui.theme.VintageWhite

@Composable
fun PokemonStats(
    statsList: List<Stats>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        statsList.forEach { stat ->
            StatRow(stat.statName, stat.baseStat.toIntOrNull() ?: 0)
        }
    }
}

@Composable
private fun StatRow(
    statName: String,
    baseStat: Int
) {
    val fraction = (baseStat / 255f).coerceIn(0f, 1f)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = statName.uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
            Text(
                text = baseStat.toString(),
                style = MaterialTheme.typography.labelMedium,
                color = RetroInk
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        SegmentedBar(fraction)
    }
}

@Composable
private fun SegmentedBar(fraction: Float) {
    val segments = 12
    val filled = if (fraction > 0f) {
        (fraction * segments).toInt().coerceIn(1, segments)
    } else 0

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, RetroInk, RoundedCornerShape(2.dp))
            .background(color = VintageWhite)
            .padding(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        repeat(segments) { i ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(10.dp)
                    .background(
                        if (i < filled) LcdGreen else LcdOff,
                        RoundedCornerShape(1.dp)
                    )
            )
        }
    }
}

@Preview
@Composable
fun PokemonStatsPreview() {
}
