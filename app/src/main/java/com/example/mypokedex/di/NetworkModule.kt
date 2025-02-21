package com.example.mypokedex.di

import com.example.mypokedex.data.remote.api.PokeApi
import com.example.mypokedex.data.remote.datasource.IPokemonDataSource
import com.example.mypokedex.data.remote.datasource.PokemonDataSource
import com.example.mypokedex.data.remote.repository.PokemonDetailsRepoImpl
import com.example.mypokedex.data.remote.repository.PokemonRepoImpl
import com.example.mypokedex.data.utils.Constants
import com.example.mypokedex.domain.repository.IPokemonDetailRepository
import com.example.mypokedex.domain.repository.IPokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
        PokemonRepoImpl(pokemonDataSource)

    @Singleton
    @Provides
    fun providesPokemonDetailsRepo(pokemonDataSource : IPokemonDataSource): IPokemonDetailRepository =
        PokemonDetailsRepoImpl(pokemonDataSource)
}
