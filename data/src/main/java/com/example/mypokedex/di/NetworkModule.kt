package com.example.mypokedex.di

import com.example.mypokedex.data.repository.PokemonRepository
import com.example.mypokedex.domain.repository.IPokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class NetworkModule {

    @Binds
    @ViewModelScoped
    abstract fun bindPokemonRepo(pokemonRepository: PokemonRepository): IPokemonRepository
}
