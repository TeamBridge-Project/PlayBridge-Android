package com.example.data.repository

import com.example.data.mapper.ResponseMapper
import com.example.data.mapper.toData
import com.example.data.service.ApiService
import com.example.domain.model.LoginModel
import com.example.domain.model.SignUpModel
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun signUp(signUpModel: SignUpModel) =
        ResponseMapper.mapperResponse(apiService.signUp(signUpModel.toData()))

    override suspend fun login(loginModel: LoginModel) =
        ResponseMapper.mapperResponse(apiService.login(loginModel.toData()))
}
