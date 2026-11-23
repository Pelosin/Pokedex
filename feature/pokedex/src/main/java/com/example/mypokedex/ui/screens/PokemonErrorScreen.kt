package com.example.mypokedex.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mypokedex.ui.R
import com.example.mypokedex.core.designsystem.R as DesignSystemR

@Composable
fun PokemonErrorScreen(
    error: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(DesignSystemR.drawable.gengar_error),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )

            Text(
                "Oh no, a gengar eat your app :(",
                fontSize = 17.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
            )

            Text(
                error,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonErrorScreenPreview() {
    PokemonErrorScreen("Oh no")
}