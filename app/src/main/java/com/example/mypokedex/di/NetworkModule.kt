package com.example.mypokedex.di

import com.example.mypokedex.data.remote.datasource.IPokemonDataSource
import com.example.mypokedex.data.remote.datasource.PokemonDataSource
import com.example.mypokedex.data.remote.repository.EvolutionRepository
import com.example.mypokedex.data.remote.repository.PokemonDetailsRepository
import com.example.mypokedex.data.remote.repository.PokemonRepository
import com.example.mypokedex.data.utils.Constants
import com.example.mypokedex.domain.repository.IEvolutionsRepository
import com.example.mypokedex.domain.repository.IPokemonDetailRepository
import com.example.mypokedex.domain.repository.IPokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providePokemonDataSource(): IPokemonDataSource {
        return PokemonDataSource(Constants.Network.BASE_URL)
    }

    @Singleton
    @Provides
    fun providesPokemonRepo(pokemonDataSource : IPokemonDataSource): IPokemonRepository =
        PokemonRepository(pokemonDataSource)

    @Singleton
    @Provides
    fun providesPokemonDetailsRepo(pokemonDataSource : IPokemonDataSource): IPokemonDetailRepository =
        PokemonDetailsRepository(pokemonDataSource)

    @Singleton
    @Provides
    fun providesEvolutionRepository(pokemonDataSource: IPokemonDataSource) : IEvolutionsRepository =
        EvolutionRepository(pokemonDataSource)
}
