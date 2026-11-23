package com.example.mypokedex.domain.model

sealed class FilterType {
    data object Empty : FilterType()
    data object Favorites : FilterType()
}
