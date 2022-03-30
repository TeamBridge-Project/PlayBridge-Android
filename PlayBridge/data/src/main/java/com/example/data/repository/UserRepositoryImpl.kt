package com.example.data.repository

import com.example.data.dto.requestbody.SignUpRequestBody
import com.example.data.mapper.toData
import com.example.data.service.ApiService
import com.example.domain.model.SignUpModel
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository{
    override suspend fun signUp(signUpModel: SignUpModel) {
        apiService.signUp(signUpModel.toData())
    }
}