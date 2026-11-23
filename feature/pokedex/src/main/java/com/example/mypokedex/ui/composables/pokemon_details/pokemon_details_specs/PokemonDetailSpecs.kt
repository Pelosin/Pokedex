package com.example.mypokedex.ui.composables.pokemon_details.pokemon_details_specs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.ui.theme.LcdGreen
import com.example.mypokedex.ui.theme.RetroInk
import kotlinx.coroutines.launch

@Composable
fun PokemonDetailSpecs(
    pokemon: Pokemon,
    navController: NavController
) {
    val scope = rememberCoroutineScope()

    val pageState = rememberPagerState(
        pageCount = {
            2
        }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
            )
    ) {
        // Retro tab row: pixel labels + LCD-green underline on the active one
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(top = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            UnderlineTab(
                label = "STATS",
                active = pageState.currentPage == 0,
                modifier = Modifier.weight(1f)
            ) {
                scope.launch { pageState.scrollToPage(0) }
            }
            UnderlineTab(
                label = "EVOLUTIONS",
                active = pageState.currentPage == 1,
                modifier = Modifier.weight(1f)
            ) {
                scope.launch { pageState.scrollToPage(1) }
            }
        }

        HorizontalPager(
            state = pageState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                ),
        ) {
            when (it) {
                0 -> {
                    PokemonAttributes(pokemon)
                }

                1 -> {
                    PokemonEvolutions(pokemon, navController)
                }
            }
        }
    }
}

@Composable
private fun UnderlineTab(
    label: String,
    active: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
            .padding(vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = if (active) RetroInk else Color(0xFF9E9E9E),
            textAlign = TextAlign.Center,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.55f)
                .height(3.dp)
                .background(
                    if (active) LcdGreen else Color.Transparent,
                    RoundedCornerShape(1.dp)
                )
        )
    }
}