package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.AuthResponse
import com.example.domain.model.LoginModel
import com.example.domain.model.SignUpModel
import com.example.domain.model.UserModel
import com.example.domain.model.UserResponse
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun signUp(signUpModel: SignUpModel): ApiResponse<AuthResponse>
    suspend fun login(loginModel: LoginModel): ApiResponse<AuthResponse>
    suspend fun getUser(query: Int): Flow<PagingData<UserModel>>
    suspend fun getProfile(uuid: String) : ApiResponse<UserResponse>
}
