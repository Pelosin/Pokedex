package com.example.mypokedex.domain.repository

interface IsFirstOpenRepository {
    suspend fun getIsFirstOpen() : Boolean
}