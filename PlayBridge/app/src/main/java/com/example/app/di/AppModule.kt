package com.example.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String = "http://braininavet.iptime.org:3000/"
}