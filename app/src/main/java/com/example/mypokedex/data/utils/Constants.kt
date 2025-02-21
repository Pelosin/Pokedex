package com.example.mypokedex.data.utils

object Constants {
    object SharedKeys {
        const val IS_FIRST_OPEN = "IS_FIRST_OPEN"
    }

    object Network {
        const val BASE_URL = "https://pokeapi.co/api/v2/"

        private const val BASE_IMAGE_URL =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"

        private const val BASE_DEITAILS_IMAGE_URL =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/"

        private const val BASE_TYPE_IMAGE_URL =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-iii/colosseum/"

        fun retrieveImageUrl(id: Int): String =
            "$BASE_IMAGE_URL$id.png"


        fun retrieveDetailImageUlr(id: Int) : String =
            "$BASE_DEITAILS_IMAGE_URL$id.png"

        fun retrieveTypeImageUrl(url: String) : String{
            val id = url.removeSuffix("/").split("/").lastOrNull()
            return "$BASE_TYPE_IMAGE_URL$id.png"
        }

    }
}