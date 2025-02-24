package com.example.mypokedex.data.utils

import android.content.SharedPreferences
import com.example.mypokedex.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun saveData(key: String, value: Any) {
        withContext(ioDispatcher) {

            val editor = sharedPreferences.edit()
            when (value) {
                is String -> {
                    editor.putString(key, value)
                }

                is Boolean -> {
                    editor.putBoolean(key, value)
                }

                is Int -> {
                    editor.putInt(key, value)
                }

                is Long -> {
                    editor.putLong(key, value)
                }

                is Float -> {
                    editor.putFloat(key, value)
                }
            }
            editor.apply()

        }
    }

    suspend fun getData(key: String, defaultValue: Any): Any {
        return withContext(ioDispatcher) {
            when (defaultValue) {
                is String -> {
                    sharedPreferences.getString(key, defaultValue) ?: defaultValue
                }

                is Boolean -> {
                    sharedPreferences.getBoolean(key, defaultValue) ?: defaultValue
                }

                is Int -> {
                    sharedPreferences.getInt(key, defaultValue) ?: defaultValue
                }

                is Long -> {
                    sharedPreferences.getLong(key, defaultValue) ?: defaultValue

                }

                is Float -> {
                    sharedPreferences.getFloat(key, defaultValue) ?: defaultValue
                }

                else -> {
                    throw IllegalArgumentException("The type specified is not valid")
                }
            }
        }
    }

    suspend fun removeData(key: String) {
        withContext(ioDispatcher) {
            val editor = sharedPreferences.edit()
            editor.remove(key)
            editor.apply()
        }
    }
}