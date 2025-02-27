package com.example.mypokedex.utils

import androidx.compose.ui.graphics.Color
import com.example.mypokedex.data.utils.Constants.Network.BASE_GIF_URL
import com.example.mypokedex.data.utils.Constants.Network.BASE_IMAGE_URL
import com.example.mypokedex.data.utils.Constants.Network.BASE_TYPE_IMAGE_URL

fun String.getTypeColor() = when (this.lowercase()) {
    "normal" -> Color(0xFFA8A878)  // Normal type - light brown
    "fighting" -> Color(0xFFC03028)  // Fighting type - red
    "flying" -> Color(0xFFA890F0)  // Flying type - light purple
    "poison" -> Color(0xFFA040A0)  // Poison type - purple
    "ground" -> Color(0xFFE0C068)  // Ground type - tan
    "rock" -> Color(0xFFB8A038)  // Rock type - brownish yellow
    "bug" -> Color(0xFFA8B820)  // Bug type - yellow-green
    "ghost" -> Color(0xFF705898)  // Ghost type - dark purple
    "steel" -> Color(0xFFB8B8D0)  // Steel type - light gray
    "fire" -> Color(0xFFFF6A00)  // Fire type - orange
    "water" -> Color(0xFF6493EB)  // Water type - blue
    "grass" -> Color(0xFF78C850)  // Grass type - green
    "electric" -> Color(0xFFF8D030)  // Electric type - yellow
    "psychic" -> Color(0xFFF85888)  // Psychic type - pink
    "ice" -> Color(0xFF98D8D8)  // Ice type - light blue
    "dragon" -> Color(0xFF7038F8)  // Dragon type - purple
    "dark" -> Color(0xFF705848)  // Dark type - dark brown
    "fairy" -> Color(0xFFF0A0F0)  // Fairy type - light pink
    "stellar" -> Color(0xFF00A0A0)  // Stellar type - teal
    "unknown" -> Color(0xFF8A8A8A)  // Unknown type - gray
    else -> Color.Gray // Default color for unrecognized types
}

fun String.retrieveImageUrl(): String =
    "$BASE_IMAGE_URL$this.png"

fun String.retrieveGifUrl(key: String): String =
    "$BASE_GIF_URL$key.gif"

fun String.retrieveTypeImageUrl(): String {
    val id = this.removeSuffix("/").split("/").lastOrNull()
    return "$BASE_TYPE_IMAGE_URL$id.png"
}

fun String.retrieveUrlId() =
    this.removeSuffix("/").split("/").last()
