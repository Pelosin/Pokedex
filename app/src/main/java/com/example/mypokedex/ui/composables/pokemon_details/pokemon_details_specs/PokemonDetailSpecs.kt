package com.example.mypokedex.ui.composables.pokemon_details.pokemon_details_specs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mypokedex.R
import com.example.mypokedex.domain.model.Pokemon
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
                colorResource(R.color.white),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.Gray,
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                ),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Stats",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        scope.launch {
                            pageState.scrollToPage(0)
                        }
                    },
                color = Color.White,
                fontWeight = if (pageState.currentPage == 0) {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                }
            )

            Text(
                text = "Evolutions",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        scope.launch {
                            pageState.scrollToPage(1)
                        }
                    },
                color = Color.White,
                fontWeight = if (pageState.currentPage == 1) {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                }
            )
        }

        HorizontalPager(
            state = pageState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    colorResource(R.color.white),
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
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

@Preview(showBackground = true)
@Composable
fun PokemonDetailsSpecPreview() {
//    PokemonDetailSpecs(
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

