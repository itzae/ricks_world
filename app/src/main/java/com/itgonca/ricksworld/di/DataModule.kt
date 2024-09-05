package com.itgonca.ricksworld.di

import com.itgonca.ricksworld.data.CharactersRepositoryImpl
import com.itgonca.ricksworld.domain.repository.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideCharactersRepository(charactersRepositoryImpl: CharactersRepositoryImpl): CharactersRepository
}