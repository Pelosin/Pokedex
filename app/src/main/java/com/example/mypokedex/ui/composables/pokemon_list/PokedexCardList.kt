package com.example.mypokedex.ui.composables.pokemon_list

import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.mypokedex.R
import com.example.mypokedex.ui.composables.loading_image.LoadingImage
import com.example.mypokedex.utils.Constants
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun PokedexCardList(fakeId : Int) {

    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ), repeatMode = RepeatMode.Reverse
        )
    )

    Card(
        modifier = Modifier
            .background(
                colorResource(R.color.transparent_gray),
                shape = RoundedCornerShape(10.dp)
            )
            .shadow(
                elevation = 25.dp,
                shape = RoundedCornerShape(10.dp)
            ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

    ) {

        val imageState = remember { mutableStateOf(true) }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Constants.Network.retrieveImageUrl(fakeId))
                // .crossfade(true)
                .build(),
            onSuccess = { imageState.value = false },
            contentDescription = null,
            error = painterResource(R.drawable.gengar),
            modifier = Modifier.size(75.dp)
                .graphicsLayer(scaleX = scale, scaleY = scale),
        )

        if (imageState.value) {
            LoadingImage(rememberDrawablePainter(
                drawable = getDrawable(
                    LocalContext.current,
                    R.drawable.pokeball_gif
                )
            ))

        }
    }
}