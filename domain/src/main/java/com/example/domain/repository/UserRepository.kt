package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.LoginModel
import com.example.domain.model.SignUpModel
import com.example.domain.model.UserModel
import com.example.domain.model.UserResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun signUp(signUpModel: SignUpModel): UserResponse
    suspend fun login(loginModel: LoginModel): UserResponse
    suspend fun getUser(query: Int): Flow<PagingData<UserModel>>
}
