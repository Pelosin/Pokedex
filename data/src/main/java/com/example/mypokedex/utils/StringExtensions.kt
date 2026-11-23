package com.example.mypokedex.utils

import com.example.mypokedex.data.utils.Constants.Network.BASE_GIF_URL
import com.example.mypokedex.data.utils.Constants.Network.BASE_IMAGE_URL
import com.example.mypokedex.data.utils.Constants.Network.BASE_TYPE_IMAGE_URL


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
