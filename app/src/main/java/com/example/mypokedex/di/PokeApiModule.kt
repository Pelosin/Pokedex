package com.example.mypokedex.di

import com.example.mypokedex.data.remote.api.PokeApi
import com.example.mypokedex.data.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PokeApiModule {

    @Provides
    @Singleton
    fun providePokemonApi() : PokeApi = Retrofit.Builder()
        .baseUrl(Constants.Network.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokeApi::class.java)
}