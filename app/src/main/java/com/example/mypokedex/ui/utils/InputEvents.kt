package com.example.mypokedex.ui.utils

sealed class InputEvents {
    data object OnFirstTimeFinish: InputEvents()
    data object OnCallApi: InputEvents()
    data class OnDetailsApiCall(val name : String): InputEvents()
}