package com.example.mypokedex.ui.composables.pokemon_details.pokemon_details_specs

import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
            .background(
                colorResource(R.color.white),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            ),
    ) {
        when (myState) {
            is UiState.Success -> {
                LazyRow(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(myState.data.size) {
                        val evolution = myState.data[it]

                        Card(
                            modifier = Modifier.padding(10.dp),
                            onClick = {
                                if (pokemonDetails.name != evolution.name)
                                    navController.navigate(
                                        Screen.Details.route + "/${evolution.name}"
                                    )
                            }
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(evolution.sprite)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = null,
                                error = painterResource(R.drawable.gengar),
                                modifier = Modifier
                                    .size(110.dp)
                                    .padding(13.dp)
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

@Preview
@Composable
fun PreviewPokemonEvolution() {
//    PokemonEvolutions(
//        PokemonDetails(
//            "Buba",
//            1,
//            emptyList(),
//            1,
//            2,
//            Sprites(OtherSprites(ShowdownSprites("a")), null, "a"),
//            emptyList()
//        ),
//        rememberNavController()
//    )
}