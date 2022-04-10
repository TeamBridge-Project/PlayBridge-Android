package com.example.domain.repository

import com.example.domain.model.SignUpModel

interface UserRepository {
    suspend fun signUp(signUpModel: SignUpModel)
}