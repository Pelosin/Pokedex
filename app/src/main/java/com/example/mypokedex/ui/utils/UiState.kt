package com.example.mypokedex.utils

sealed class UiState<out T : Any> {
    data object Loading : UiState<Nothing>()
    class Success<out T : Any>(val data: T?) : UiState<T>()
    class Error(val message: String) : UiState<Nothing>()
}