package com.example.domain.repository

import com.example.domain.model.LoginModel
import com.example.domain.model.UserResponse
import com.example.domain.model.SignUpModel

interface UserRepository {
    suspend fun signUp(signUpModel: SignUpModel)
    suspend fun login(loginModel : LoginModel) : UserResponse
}