package com.example.mypokedex.ui.composables.pokedex_topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mypokedex.ui.theme.LcdDark
import com.example.mypokedex.ui.theme.LcdGreen
import com.example.mypokedex.ui.theme.LcdPale

private val LensBlue = Color(0xFF3B4CCA)
private val LightRed = Color(0xFFFF7F7F)
private val LightYellow = Color(0xFFFFDE00)
private val LightGreen = Color(0xFF71D44C)

@ExperimentalMaterial3Api
@Composable
fun PokedexTopbar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LcdDark,
            titleContentColor = LcdGreen,
            navigationIconContentColor = LcdPale
        ),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // The device lens
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .background(Color.White, CircleShape)
                        .padding(4.dp)
                        .background(LensBlue, CircleShape)
                )
                Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Box(Modifier.size(5.dp).background(LightRed, CircleShape))
                        Box(Modifier.size(5.dp).background(LightYellow, CircleShape))
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Box(Modifier.size(5.dp).background(LightGreen, CircleShape))
                    }
                }
                Text(
                    "POKEDEX",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier.padding(start = 2.dp, bottom = 2.dp)
                )
            }
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 20.dp, start = 10.dp)
                    .size(27.dp)
            )
        },
        scrollBehavior = scrollBehavior
    )
}
