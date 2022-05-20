package com.example.domain.di

import com.example.domain.common.LoginValidator
import com.example.domain.common.SignUpValidator
import com.example.domain.common.impl.LoginValidatorImpl
import com.example.domain.common.impl.SignUpValidatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DomainModule {

    @Binds
    @Singleton
    fun bindSignUpValidator(
        validator: SignUpValidatorImpl
    ): SignUpValidator

    @Binds
    @Singleton
    fun bindLoginValidator(
        validator: LoginValidatorImpl
    ): LoginValidator

}