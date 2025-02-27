package com.example.mypokedex.ui.utils

sealed class InputEvents {
    data object OnCallApi : InputEvents()
    data class SearchQueryInput(val query: String) : InputEvents()
    data class OnDetailsApiCall(val name: String) : InputEvents()
    data class OnGetEvolutions(val name: String) : InputEvents()
    data object OnAToZSort : InputEvents()
    data object OnZToASort : InputEvents()
    data object OnTypeFilter : InputEvents()
    data object OnFavFilter : InputEvents()
}