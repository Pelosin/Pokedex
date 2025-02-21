package com.example.mypokedex.data.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor(
    private val context: Context
) {
    private var sharedPreferences: SharedPreferences? = null

    init {
        CoroutineScope(Dispatchers.IO).launch {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        }
    }

    suspend fun saveData(key: String, value: Any) {
        withContext(Dispatchers.IO) {
            sharedPreferences?.let {
                val editor = it.edit()
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
    }

    suspend fun getData(key: String, defaultValue: Any): Any {
        return withContext(Dispatchers.IO) {
            when (defaultValue) {
                is String -> {
                    sharedPreferences?.getString(key, defaultValue) ?: defaultValue
                }
                is Boolean -> {
                    sharedPreferences?.getBoolean(key, defaultValue) ?: defaultValue
                }
                is Int -> {
                    sharedPreferences?.getInt(key, defaultValue) ?: defaultValue
                }
                is Long -> {
                    sharedPreferences?.getLong(key, defaultValue) ?: defaultValue

                }
                is Float -> {
                    sharedPreferences?.getFloat(key, defaultValue) ?: defaultValue
                }
                else -> {throw Exception()}
            }
        }
    }

    suspend fun removeData(key: String) {
        withContext(Dispatchers.IO) {
            sharedPreferences?.let {
                val editor = it.edit()
                editor.remove(key)
                editor.apply()
            }
        }
    }
}