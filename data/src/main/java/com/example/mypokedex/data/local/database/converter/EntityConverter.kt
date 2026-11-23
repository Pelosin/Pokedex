package com.example.mypokedex.data.local.database.converter

import androidx.room.TypeConverter
import com.example.mypokedex.domain.model.Stats
import com.example.mypokedex.domain.model.Type
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EntityConverter {
    @TypeConverter
    fun fromList(types: List<String>): String = types.joinToString(",")

    @TypeConverter
    fun toList(data: String): List<String> = data.split(",")

    @TypeConverter
    fun fromMap(value: Map<String, String>): String {
        return Gson().toJson(value)  // Serialize the Map to a JSON String
    }

    @TypeConverter
    fun toMap(value: String): Map<String, String> {
        val type = object : TypeToken<Map<String, String>>() {}.type
        return Gson().fromJson(value, type)  // Deserialize the JSON String back to a Map
    }

    @TypeConverter
    fun fromType(list: List<Type>): String =
        list.joinToString(",") {
            "${it.name}x${it.sprite}"
        }

    @TypeConverter
    fun toTypes(data: String): List<Type> {
        if (data.isEmpty())
            return emptyList()
        return data.split(",").map {
            val parts = it.split("x")
            Type(parts[0], parts[1])
        }
    }

    @TypeConverter
    fun fromStats(list: List<Stats>): String =
        list.joinToString(",") {
            "${it.statName}:${it.baseStat}"
        }

    @TypeConverter
    fun toStats(data: String): List<Stats> {
        if (data.isEmpty())
            return emptyList()
        return data.split(",").map {
            val parts = it.split(":")
            Stats(parts[0], parts[1])
        }
    }
}