package com.example.mypokedex.ui.composables.pokemon_list

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.mypokedex.ui.R
import com.example.mypokedex.core.designsystem.R as DesignSystemR
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.utils.getTypeColor
import com.google.accompanist.drawablepainter.rememberDrawablePainter

private val RetroInk = Color(0xFF1A1A1E)
private val LcdGreen = Color(0xFF9BBC0F)

@Composable
fun PokemonListItem(
    pokemon: Pokemon,
    sprite: String?,
    onClick: () -> Unit
) {
    val typeColor = pokemon.types.firstOrNull()?.name?.getTypeColor() ?: Color.Gray

    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(2.dp, RetroInk),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = { onClick() },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            // LCD screen where the sprite lives
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .border(2.dp, RetroInk, RoundedCornerShape(4.dp))
                    .background(typeColor.copy(alpha = 0.3f), RoundedCornerShape(4.dp))
            ) {
                val imageState = remember { mutableStateOf(true) }
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(sprite)
                        .crossfade(true)
                        .build(),
                    onSuccess = { imageState.value = false },
                    contentDescription = pokemon.name,
                    filterQuality = FilterQuality.None,
                    modifier = Modifier
                        .size(88.dp)
                        .graphicsLayer(scaleX = scale, scaleY = scale),
                )
                if (imageState.value) {
                    Image(
                        painter = rememberDrawablePainter(
                            drawable = getDrawable(
                                LocalContext.current,
                                DesignSystemR.drawable.pokeball_gif
                            )
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .alpha(0.6f)
                            .size(50.dp)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp, start = 4.dp, end = 2.dp)
            ) {
                Column {
                    Text(
                        "#${pokemon.id.toString().padStart(3, '0')}",
                        fontSize = 9.sp,
                        letterSpacing = 1.sp,
                        lineHeight = 12.sp,
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.alpha(0.8f)
                    )
                    Text(
                        pokemon.name.replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = if (pokemon.favorite) "Favorite" else "Not favorite",
                    tint = if (pokemon.favorite) Color(0xFFFFC107) else LcdGreen,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}