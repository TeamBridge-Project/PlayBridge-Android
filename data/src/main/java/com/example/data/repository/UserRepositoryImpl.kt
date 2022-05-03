package com.example.data.repository

import com.example.data.di.IoDispatcher
import com.example.data.mapper.ResponseMapper
import com.example.data.mapper.toData
import com.example.data.service.UserService
import com.example.domain.model.LoginModel
import com.example.domain.model.SignUpModel
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: UserService,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher
) : UserRepository {
    override suspend fun signUp(signUpModel: SignUpModel) =
        withContext(defaultDispatcher) {
            ResponseMapper.mapperResponse(apiService.signUp(signUpModel.toData()))
        }

    override suspend fun login(loginModel: LoginModel) =
        withContext(defaultDispatcher) {
            ResponseMapper.mapperResponse(apiService.login(loginModel.toData()))
        }
}
