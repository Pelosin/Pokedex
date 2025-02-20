package com.example.mypokedex.ui.composables.pokemon_details.pokemon_details_image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.gif.GifDecoder
import coil3.request.ImageRequest
import com.example.mypokedex.R
import com.example.mypokedex.domain.model.PokemonDetails

@Composable
fun PokemonDetailsImage(
    sprite : String,
    color: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .background(color)
    )
    {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(sprite)
                .decoderFactory(GifDecoder.Factory())
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center),
            error = {
                if (LocalInspectionMode.current) {
                    Image(
                        painter = painterResource(R.drawable.gengar),
                        contentDescription = null,
                    )
                }
            }
        )
    }
}