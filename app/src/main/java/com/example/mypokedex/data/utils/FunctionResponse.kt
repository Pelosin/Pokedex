package com.example.mypokedex.utils

sealed class FunctionResponse<T>(val data: T?, val message: String?) {
    class Success<T>(data: T?) : FunctionResponse<T>(data, null)
    class Error<T>(message: String) : FunctionResponse<T>(null, message)
}