package com.example.mypokedex.ui.composables.pokedex_topbar

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.twotone.Star
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
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
                fontFamily = FontFamily(Font(R.font.pokefont)),
                color = Color.White,
                textAlign = TextAlign.Center
            )

        },
        actions = {
            IconButton(onClick = { isFav.value = !isFav.value }) {
                if(isFav.value) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color.Yellow,
                    )
                } else {
                    Icon(
                        imageVector = Icons.TwoTone.Star,
                        contentDescription = null,
                        tint = Color.Yellow,
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
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }
    )
}