package com.example.mypokedex.ui.composables.pokemon_details

import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.gif.GifDecoder
import coil3.request.ImageRequest
import com.example.mypokedex.R
import com.example.mypokedex.ui.viewmodels.PokemonDetailsViewModel
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun PokemonDetailScreen(
    name: String,
    
) {

    Box (modifier = Modifier
        .fillMaxSize(0.5f)
        .background(Color(0xffb5fa87))){


        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/1.gif")
                .decoderFactory(GifDecoder.Factory())
                .build(),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )



    }


}

@Preview
@Composable
fun PreviewPokemonDetails() {

}