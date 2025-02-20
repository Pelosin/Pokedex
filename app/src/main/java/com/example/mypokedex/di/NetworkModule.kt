package com.example.mypokedex.di

import com.example.mypokedex.data.api.PokeApi
import com.example.mypokedex.data.repository_impl.PokemonDetailsRepoImpl
import com.example.mypokedex.data.repository_impl.PokemonRepoImpl
import com.example.mypokedex.domain.repository.PokemonDetailRepository
import com.example.mypokedex.domain.repository.PokemonRepository
import com.example.mypokedex.utils.Constants
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
    fun providePokeApi(): PokeApi = Retrofit.Builder()
        .baseUrl(Constants.Network.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokeApi::class.java)

    @Singleton
    @Provides
    fun providesPokemonRepo(pokeApi: PokeApi): PokemonRepository =
        PokemonRepoImpl(pokeApi)

    @Singleton
    @Provides
    fun providesPokemonDetailsRepo(pokeApi: PokeApi): PokemonDetailRepository =
        PokemonDetailsRepoImpl(pokeApi)
}
