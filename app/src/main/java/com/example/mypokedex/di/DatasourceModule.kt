package com.example.mypokedex.di

import com.example.mypokedex.data.remote.source.IPokemonDataSource
import com.example.mypokedex.data.remote.source.PokemonDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Binds
    @Singleton
    abstract fun bindPokemonDataSource(pokemonDataSource: PokemonDataSource): IPokemonDataSource
}