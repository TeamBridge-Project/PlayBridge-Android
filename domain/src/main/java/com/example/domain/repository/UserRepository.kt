package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.user.AuthResponse
import com.example.domain.model.user.LoginModel
import com.example.domain.model.user.SignUpModel
import com.example.domain.model.user.UserModel
import com.example.domain.model.user.UserResponse
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun signUp(signUpModel: SignUpModel): ApiResponse<AuthResponse>
    suspend fun login(loginModel: LoginModel): ApiResponse<AuthResponse>
    suspend fun getUser(query: Int): Flow<PagingData<UserModel>>
    suspend fun getProfile(uuid: String) : ApiResponse<UserResponse>
}
