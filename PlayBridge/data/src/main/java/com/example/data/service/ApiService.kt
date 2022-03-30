package com.example.data.service

import com.example.data.dto.requestbody.SignUpRequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/api")
    suspend fun signUp(@Body signUpRequestBody: SignUpRequestBody)
}