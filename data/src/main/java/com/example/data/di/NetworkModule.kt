package com.example.data.di

import com.example.data.service.UserService
import com.example.local.datastore.DataStoreManager
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named("baseUrl") baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor
    ) =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun providesOkhttpInterceptor(
        dataStore: DataStoreManager
    ): Interceptor =
        Interceptor { chain: Interceptor.Chain ->
            val original: Request = chain.request()
            val requestBuilder = original.newBuilder()
            val accessToken = runBlocking {
                dataStore.accessToken.first()
            }
            val refreshToken = runBlocking {
                dataStore.refreshToken.first()
            }
            requestBuilder.addHeader("X-Access-Token", accessToken)
                .addHeader("X-Refresh-Token", refreshToken)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
}
