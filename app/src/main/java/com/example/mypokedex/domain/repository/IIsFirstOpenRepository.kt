package com.example.mypokedex.domain.repository

interface IIsFirstOpenRepository {
    suspend fun getIsFirstOpen(): Boolean
}