package com.example.mypokedex.data.local.memory.repository

import com.example.mypokedex.data.utils.Constants
import com.example.mypokedex.data.utils.SharedPreferencesHelper
import com.example.mypokedex.di.IoDispatcher
import com.example.mypokedex.domain.repository.IIsFirstOpenRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import javax.inject.Inject

class IsFirstOpenRepository @Inject constructor(
    private val sharedPreferences: SharedPreferencesHelper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : IIsFirstOpenRepository {
    override suspend fun getIsFirstOpen(): Boolean {
        val isFirstOpenDeferred = CoroutineScope(ioDispatcher).async {
            val isFirstOpen = sharedPreferences.getData(
                Constants.SharedKeys.IS_FIRST_OPEN, true
            ) as Boolean

            sharedPreferences.saveData(Constants.SharedKeys.IS_FIRST_OPEN, false)

            isFirstOpen
        }

        return isFirstOpenDeferred.await()
    }
}