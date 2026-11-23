package com.example.mypokedex.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.mypokedex.core.designsystem.R

// Retro: everything speaks "Pokemon Classic" pixel type
private val Retro = FontFamily(Font(R.font.pokefont))

val Typography = Typography(
    displayLarge = TextStyle(fontFamily = Retro, fontSize = 44.sp, lineHeight = 50.sp),
    headlineMedium = TextStyle(fontFamily = Retro, fontSize = 26.sp, lineHeight = 32.sp),
    headlineSmall = TextStyle(fontFamily = Retro, fontSize = 22.sp, lineHeight = 28.sp),
    titleLarge = TextStyle(fontFamily = Retro, fontSize = 20.sp, lineHeight = 26.sp),
    titleMedium = TextStyle(fontFamily = Retro, fontSize = 15.sp, lineHeight = 22.sp, letterSpacing = 1.sp),
    titleSmall = TextStyle(fontFamily = Retro, fontSize = 12.sp, lineHeight = 18.sp, letterSpacing = 1.sp),
    bodyLarge = TextStyle(fontFamily = Retro, fontSize = 16.sp, lineHeight = 24.sp, letterSpacing = 0.5.sp),
    bodyMedium = TextStyle(fontFamily = Retro, fontSize = 13.sp, lineHeight = 20.sp),
    bodySmall = TextStyle(fontFamily = Retro, fontSize = 10.sp, lineHeight = 16.sp),
    labelLarge = TextStyle(fontFamily = Retro, fontSize = 13.sp, lineHeight = 18.sp, letterSpacing = 1.sp),
    labelMedium = TextStyle(fontFamily = Retro, fontSize = 11.sp, lineHeight = 16.sp, letterSpacing = 1.sp),
    labelSmall = TextStyle(fontFamily = Retro, fontSize = 9.sp, lineHeight = 14.sp, letterSpacing = 1.sp)
)
