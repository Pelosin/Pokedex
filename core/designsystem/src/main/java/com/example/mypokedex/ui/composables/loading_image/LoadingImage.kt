package com.example.mypokedex.ui.composables.loading_image

import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mypokedex.ui.R
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun LoadingImage(
    painter: Painter
) {
    Image(
        modifier = Modifier
            .alpha(0.6f)
            .size(50.dp),
        painter = painter,
        contentDescription = null
    )
}

@Preview(showBackground = true)
@Composable
fun LoadingImagePreview() {
    LoadingImage(
        rememberDrawablePainter(
            drawable = getDrawable(
                LocalContext.current,
                R.drawable.pokeball_gif
            )
        )
    )
}