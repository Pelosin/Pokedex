package com.example.mypokedex.data.local.memory.repository

import com.example.mypokedex.data.utils.Constants
import com.example.mypokedex.data.utils.SharedPreferencesHelper
import com.example.mypokedex.domain.repository.IIsFirstOpenRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class IsFirstOpenRepository @Inject constructor(
    private val sharedPreferences: SharedPreferencesHelper
) : IIsFirstOpenRepository {
    override suspend fun getIsFirstOpen(): Boolean {
        val isFirstOpenDeferred = CoroutineScope(Dispatchers.IO).async {
            val isFirstOpen = sharedPreferences.getData(
                Constants.SharedKeys.IS_FIRST_OPEN, true
            ) as Boolean

            sharedPreferences.saveData(Constants.SharedKeys.IS_FIRST_OPEN, false)

            isFirstOpen
        }

        return isFirstOpenDeferred.await()
    }
}