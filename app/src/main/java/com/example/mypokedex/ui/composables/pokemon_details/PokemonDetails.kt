package com.example.mypokedex.ui.composables.pokemon_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.model.Type
import com.example.mypokedex.ui.composables.pokedex_topbar.PokemonDetailsTopbar
import com.example.mypokedex.ui.composables.pokemon_details.pokemon_details_image.PokemonDetailsImage
import com.example.mypokedex.ui.composables.pokemon_details.pokemon_details_specs.PokemonDetailSpecs
import com.example.mypokedex.utils.getTypeColor

@ExperimentalMaterial3Api
@Composable
fun PokemonDetails(
    pokemon: Pokemon,
    navController: NavController
) {

    val types = pokemon.types.takeIf { it.isNotEmpty() }
        ?: listOf(Type("Unknown", ""))

    val color =
        types.first().name.getTypeColor()


    Scaffold(
        topBar = {
            PokemonDetailsTopbar(
                Modifier,
                navController,
                pokemon,
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
            val sprite = pokemon.sprites.let { map ->
                map["gif"] ?: map["default"] ?: ""
            }
            PokemonDetailsImage(
                sprite,
                color
            )
            PokemonDetailSpecs(
                pokemon,
                navController
            )
        }
    }


}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun PokeDetailsPreview() {
//    PokemonDetails(
//        PokemonDetails(
//            "Buba",
//            1,
//            listOf(PokemonDetailsTypeResponse(0, TypeDetail("ghost", "url"))),
//            1,
//            2,
//            Sprites(OtherSprites(ShowdownSprites("a")), null, "a"),
//            emptyList()
//        ),
//        rememberNavController()
//    )
}