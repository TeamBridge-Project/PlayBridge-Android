package com.example.domain.repository

import com.example.domain.model.LoginModel
import com.example.domain.model.SignUpModel
import com.example.domain.model.UserResponse

interface UserRepository {
    suspend fun signUp(signUpModel: SignUpModel): UserResponse
    suspend fun login(loginModel: LoginModel): UserResponse
}
