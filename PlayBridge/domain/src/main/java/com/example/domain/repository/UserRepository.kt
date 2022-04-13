package com.example.domain.repository

import com.example.domain.model.LoginModel
import com.example.domain.model.Response
import com.example.domain.model.SignUpModel

interface UserRepository {
    suspend fun signUp(signUpModel: SignUpModel)
    suspend fun login(loginModel : LoginModel) : Response
}