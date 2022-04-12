package com.example.data.repository

import com.example.data.mapper.toData
import com.example.data.mapper.toDomain
import com.example.data.service.ApiService
import com.example.domain.model.LoginModel
import com.example.domain.model.Response
import com.example.domain.model.SignUpModel
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun signUp(signUpModel: SignUpModel) {
        apiService.signUp(signUpModel.toData())
    }

    override suspend fun login(loginModel: LoginModel): Result<Response> {
        val result = apiService.login(loginModel.toData()).toDomain()
        if(result.status){
            return Result.success(result)
        }else{
            return Result.failure(Exception())
        }
    }

}