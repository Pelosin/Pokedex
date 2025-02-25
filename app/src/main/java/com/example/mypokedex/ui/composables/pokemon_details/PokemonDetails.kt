package com.example.mypokedex.ui.composables.pokemon_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mypokedex.domain.model.OtherSprites
import com.example.mypokedex.domain.model.PokemonDetails
import com.example.mypokedex.domain.model.ShowdownSprites
import com.example.mypokedex.domain.model.Sprites
import com.example.mypokedex.domain.model.Type
import com.example.mypokedex.domain.model.TypeDetail
import com.example.mypokedex.ui.composables.pokedex_topbar.PokemonDetailsTopbar
import com.example.mypokedex.ui.composables.pokemon_details.pokemon_details_image.PokemonDetailsImage
import com.example.mypokedex.ui.composables.pokemon_details.pokemon_details_specs.PokemonDetailSpecs
import com.example.mypokedex.ui.utils.PokemonColor.getPokemonTypeColor

@ExperimentalMaterial3Api
@Composable
fun PokemonDetails(
    pokemonDetails: PokemonDetails,
    navController: NavController
) {
    val color =
        getPokemonTypeColor(pokemonDetails.types[0].type.name)


    Scaffold(
        topBar = {
            PokemonDetailsTopbar(
                Modifier,
                navController,
                pokemonDetails.name.replaceFirstChar { it.uppercase() },
                color
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color)
        ) {
            PokemonDetailsImage(
                pokemonDetails.sprites.other.showdown.frontDefault,
                color
            )
            PokemonDetailSpecs(
                pokemonDetails
            )
        }
    }


}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun PokeDetailsPreview() {
    PokemonDetails(
        PokemonDetails(
            "Buba",
            1,
            listOf(Type(0, TypeDetail("ghost", "url"))),
            1,
            2,
            Sprites(OtherSprites(ShowdownSprites("a"))),
            emptyList()
        ),
        rememberNavController()
    )
}