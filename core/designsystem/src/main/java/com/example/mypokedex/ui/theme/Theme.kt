package com.example.mypokedex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Red80,
    onPrimary = PokedexCharcoal,
    primaryContainer = PokedexRedDark,
    onPrimaryContainer = PokedexCreme,
    secondary = GreySteel80,
    tertiary = Blue80,
    background = PokedexCharcoal,
    onBackground = PokedexCreme,
    surface = SurfaceDark,
    onSurface = PokedexCreme,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = GreySteel80
)

private val LightColorScheme = lightColorScheme(
    primary = Red40,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    primaryContainer = PokedexRedLight,
    onPrimaryContainer = PokedexCharcoal,
    secondary = GreySteel40,
    tertiary = Blue40,
    background = VintageWhite,
    onBackground = PokedexCharcoal,
    surface = VintageWhite,
    onSurface = PokedexCharcoal,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = GreySteel40
)

@Composable
fun MyPokedexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
