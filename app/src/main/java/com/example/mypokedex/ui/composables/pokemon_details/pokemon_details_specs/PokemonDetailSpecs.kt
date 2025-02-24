package com.example.mypokedex.ui.composables.pokemon_details.pokemon_details_specs

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mypokedex.domain.model.PokemonDetails

@Composable
fun PokemonDetailSpecs(
    pokemonDetails: PokemonDetails
) {

    val pagerState = rememberPagerState(pageCount = {
        10
    })
    HorizontalPager(
        state = pagerState
    ) {
        when(it) {
            0 -> {
                PokemonAttributes(pokemonDetails)
            }

            1 -> {
                PokemonEvolutions(pokemonDetails)
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun PokemonDetailsSpecPreview() {
   //PokemonDetailSpecs(
   //    listOf(Type(1, TypeDetail("Grass", "a"))),
   //    listOf(Stats(65, StatType("Hp"))),
   //    104,
   //    2
   //)
}

