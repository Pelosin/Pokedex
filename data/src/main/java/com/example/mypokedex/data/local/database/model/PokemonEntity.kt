package com.example.mypokedex.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mypokedex.domain.model.Stats
import com.example.mypokedex.domain.model.Type

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val name: String,
    val id: Int = 0,
    val types: List<Type> = emptyList(),
    val weight: Int = 0,
    val height: Int = 0,
    val sprites: Map<String, String> = emptyMap(),
    val statsList: List<Stats> = emptyList(),
    //val evolutions: List<Evolution> = emptyList(),
    val favorite: Boolean = false,
    val isLoaded: Boolean = false

)
