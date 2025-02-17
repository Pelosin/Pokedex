package com.example.mypokedex.ui.ui_state

sealed class UiState<out T : Any> {
    data object Loading : UiState<Nothing>()
    class Success<out T : Any>(val data: T?) : UiState<T>()
    class Error(message: String) : UiState<Nothing>()
}