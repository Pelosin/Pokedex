package com.example.mypokedex.ui.composables.pokedex_topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mypokedex.ui.R
import com.example.mypokedex.core.designsystem.R as DesignSystemR
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.ui.utils.InputEvents
import com.example.mypokedex.ui.viewmodels.PokemonDetailsViewModel

@ExperimentalMaterial3Api
@Composable
fun PokemonDetailsTopbar(
    modifier: Modifier = Modifier,
    navController: NavController,
    pokemon: Pokemon,
    color: Color
) {

    val pokemonDetailsViewModel: PokemonDetailsViewModel = hiltViewModel()

    TopAppBar(
        modifier = modifier.background(color),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color
        ),
        title = {
            Text(
                pokemon.name.replaceFirstChar { it.uppercase() },
                modifier = Modifier.padding(bottom = 4.dp),
                fontFamily = FontFamily(Font(DesignSystemR.font.pokefont)),
                color = Color.White,
                textAlign = TextAlign.Center,
            )

        },
        actions = {
            IconButton(onClick = {
                pokemonDetailsViewModel.onInputEvent(InputEvents.OnFavClick(pokemon.name))
            }) {
                if (pokemon.favorite) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color.Yellow,
                    )
                } else {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_star_outline_24),
                        contentDescription = null,
                        tint = Color.Yellow,
                        modifier = Modifier
                            .size(27.dp)
                            .padding()
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Sharp.ArrowBack,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DetailsTopbarPreview() {
    // PokemonDetailsTopbar(Modifier, rememberNavController(), "Bulbasaur", false,Color(0xFF78C850))
}