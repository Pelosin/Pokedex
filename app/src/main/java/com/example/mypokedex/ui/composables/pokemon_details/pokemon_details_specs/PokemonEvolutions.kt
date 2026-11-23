package com.example.mypokedex.ui.composables.pokemon_details.pokemon_details_specs

import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.mypokedex.R
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.ui.composables.loading_image.LoadingImage
import com.example.mypokedex.ui.composables.navigation.Screen
import com.example.mypokedex.ui.screens.PokemonErrorScreen
import com.example.mypokedex.ui.utils.InputEvents
import com.example.mypokedex.ui.utils.UiState
import com.example.mypokedex.ui.theme.LcdGreen
import com.example.mypokedex.ui.theme.RetroInk
import com.example.mypokedex.ui.viewmodels.EvolutionsViewModel
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun PokemonEvolutions(
    pokemonDetails: Pokemon,
    navController: NavController
) {
    val evolutionsViewModel = hiltViewModel<EvolutionsViewModel>()

    evolutionsViewModel.onInputEvent(InputEvents.OnGetEvolutions(pokemonDetails.name))

    val myState = evolutionsViewModel.viewState.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (myState) {
            is UiState.Success -> {
                LazyRow(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(myState.data.size) {
                        val evolution = myState.data[it]
                        val isCurrent = evolution.name == pokemonDetails.name

                        // pixel arrow between chain steps
                        if (it > 0) {
                            Text(
                                "->",
                                style = MaterialTheme.typography.titleLarge,
                                color = RetroInk,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(6.dp)
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(100.dp)
                                    .border(
                                        2.dp,
                                        if (isCurrent) LcdGreen else RetroInk,
                                        RoundedCornerShape(6.dp)
                                    )
                                    .background(LcdGreen.copy(alpha = 0.22f), RoundedCornerShape(6.dp))
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(evolution.sprite)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = evolution.name,
                                    filterQuality = FilterQuality.None,
                                    error = painterResource(R.drawable.gengar),
                                    modifier = Modifier.size(84.dp)
                                )
                            }
                            Text(
                                evolution.name.uppercase(),
                                style = MaterialTheme.typography.labelMedium,
                                color = if (isCurrent) RetroInk else Color(0xFF9E9E9E),
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            // LCD underline marks the current one (same language as the tabs)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .height(3.dp)
                                    .background(
                                        if (isCurrent) LcdGreen else Color.Transparent,
                                        RoundedCornerShape(1.dp)
                                    )
                            )
                        }
                    }
                }
            }

            UiState.Loading -> {
                LoadingImage(
                    rememberDrawablePainter(
                        drawable = getDrawable(
                            LocalContext.current,
                            R.drawable.pokeball_gif
                        )
                    )
                )
            }

            is UiState.Error -> {
                PokemonErrorScreen("No evolutions found")
            }
        }
    }
}