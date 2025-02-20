package com.example.mypokedex.ui.composables.loading_image

import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.mypokedex.R
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun LoadingImage(
    painter : Painter
) {
    Image(
        modifier = Modifier
            .alpha(0.6f)
            .size(50.dp),
        painter = painter,
        contentDescription = null
    )
}