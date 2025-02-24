package com.example.mypokedex.di

import com.example.mypokedex.data.local.memory.repository.IsFirstOpenRepository
import com.example.mypokedex.domain.repository.IIsFirstOpenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class FirstOpenModule {

    @Binds
    @ViewModelScoped
    abstract fun bindIsFirstRepository(isFirstOpenRepository: IsFirstOpenRepository) : IIsFirstOpenRepository
}