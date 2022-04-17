package com.example.app.di

import android.content.Context
import com.example.local.datastore.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String = "http://braininavet.iptime.org:3000/"

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context) : DataStoreManager {
        return  DataStoreManager(context)
    }
}