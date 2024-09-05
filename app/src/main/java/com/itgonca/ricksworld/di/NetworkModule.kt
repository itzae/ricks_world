package com.itgonca.ricksworld.di

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideApolloClient(): ApolloClient = ApolloClient.Builder().serverUrl("https://rickandmortyapi.com/graphql").build()
}