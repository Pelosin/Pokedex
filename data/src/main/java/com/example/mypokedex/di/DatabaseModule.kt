package com.example.mypokedex.di

import android.content.Context
import androidx.room.Room
import com.example.mypokedex.data.local.database.core.PokedexDatabase
import com.example.mypokedex.data.local.database.dao.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesPokedexDatabase(@ApplicationContext context: Context): PokedexDatabase {
        return Room.databaseBuilder(
            context,
            PokedexDatabase::class.java,
            "PokedexDb"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesPokemonDao(pokemonDb: PokedexDatabase): PokemonDao = pokemonDb.pokemonDao()

}