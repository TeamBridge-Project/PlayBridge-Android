package com.example.data.service

import com.example.data.dto.requestbody.LoginRequestBody
import com.example.data.dto.requestbody.SignUpRequestBody
import com.example.domain.model.AuthResponse
import com.example.domain.model.UserResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("/user")
    suspend fun signUp(@Body signUpRequestBody: SignUpRequestBody): ApiResponse<AuthResponse>

    @POST("/user/login")
    suspend fun login(@Body loginRequestBody: LoginRequestBody): ApiResponse<AuthResponse>

    @GET("/user")
    suspend fun getUserList(@Query("page") page: Int): Response<UserResponse>
}
