package com.example.mypokedex.data.local.database.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mypokedex.data.local.database.converter.EntityConverter
import com.example.mypokedex.data.local.database.dao.PokemonDao
import com.example.mypokedex.data.local.database.model.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = true)
@TypeConverters(EntityConverter::class)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}