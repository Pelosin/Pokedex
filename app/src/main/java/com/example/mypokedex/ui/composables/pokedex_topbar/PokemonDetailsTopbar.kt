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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mypokedex.R

@ExperimentalMaterial3Api
@Composable
fun PokemonDetailsTopbar(
    modifier: Modifier = Modifier,
    navController: NavController,
    title: String,
    color: Color
) {

    val isFav = remember { mutableStateOf(false) }

    TopAppBar(
        modifier = modifier.background(color),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color
        ),
        title = {
            Text(
                title,
                modifier = Modifier.padding(bottom = 4.dp),
                fontFamily = FontFamily(Font(R.font.pokefont)),
                color = Color.White,
                textAlign = TextAlign.Center,
            )

        },
        actions = {
            IconButton(onClick = { isFav.value = !isFav.value }) {
                if (isFav.value) {
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
                        modifier = Modifier.size(27.dp)
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
    PokemonDetailsTopbar(Modifier, rememberNavController(), "Bulbasaur", Color(0xFF78C850))
}