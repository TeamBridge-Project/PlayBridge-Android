package com.example.data.repository

import com.example.data.mapper.Mapper
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
        Mapper.mapperResponse(apiService.signUp(signUpModel.toData()))


    override suspend fun login(loginModel: LoginModel) =
        Mapper.mapperResponse(apiService.login(loginModel.toData()))

}