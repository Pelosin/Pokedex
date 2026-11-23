package com.example.mypokedex.ui.screens

import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mypokedex.R
import com.example.mypokedex.ui.theme.LcdDark
import com.example.mypokedex.ui.theme.LcdGreen
import com.example.mypokedex.ui.theme.LcdPale
import com.example.mypokedex.ui.theme.RetroInk
import com.google.accompanist.drawablepainter.rememberDrawablePainter

private val LensBlue = Color(0xFF3B4CCA)
private val LightRed = Color(0xFFFF7F7F)
private val LightYellow = Color(0xFFFFDE00)
private val LightGreen = Color(0xFF71D44C)

@Composable
fun FirstTimeScreen(
    onClick: () -> Unit
) {
    // hard pixel blink: full on / dim, no fade
    val infiniteTransition = rememberInfiniteTransition()
    val blink by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 450, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Device lens, same face as the topbar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(26.dp)
                        .background(Color.White, CircleShape)
                        .padding(5.dp)
                        .background(LensBlue, CircleShape)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Box(Modifier.size(6.dp).background(LightRed, CircleShape))
                    Box(Modifier.size(6.dp).background(LightYellow, CircleShape))
                    Box(Modifier.size(6.dp).background(LightGreen, CircleShape))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // The LCD boot screen
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(4.dp, RetroInk, RoundedCornerShape(10.dp))
                    .background(LcdDark, RoundedCornerShape(10.dp))
                    .padding(horizontal = 20.dp, vertical = 28.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "POKEDEX",
                        style = MaterialTheme.typography.headlineMedium,
                        color = LcdGreen,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "SYSTEM v1.0",
                        style = MaterialTheme.typography.labelMedium,
                        color = LcdPale,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 6.dp)
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    Text(
                        "WELCOME,",
                        style = MaterialTheme.typography.bodyMedium,
                        color = LcdGreen,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "TRAINER!",
                        style = MaterialTheme.typography.bodyMedium,
                        color = LcdGreen,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        "> PRESS START <",
                        style = MaterialTheme.typography.labelLarge,
                        color = LcdGreen,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.alpha(if (blink > 0.5f) 1f else 0.2f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Spinning Pokéball
            Image(
                painter = rememberDrawablePainter(
                    drawable = getDrawable(
                        LocalContext.current,
                        R.drawable.pokeball_gif
                    )
                ),
                contentDescription = null,
                modifier = Modifier.size(90.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFirstTimeScreen() {
    FirstTimeScreen { }
}
