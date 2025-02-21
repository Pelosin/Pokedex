package com.example.mypokedex.data.local.memory.repository

import android.content.SharedPreferences
import com.example.mypokedex.data.utils.Constants
import com.example.mypokedex.domain.repository.IsFirstOpenRepository
import javax.inject.Inject

class IsFirstOpenRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : IsFirstOpenRepository{
    override suspend fun getIsFirstOpen(): Boolean =
        sharedPreferences.getBoolean(Constants.SharedKeys.IS_FIRST_OPEN, false)
}