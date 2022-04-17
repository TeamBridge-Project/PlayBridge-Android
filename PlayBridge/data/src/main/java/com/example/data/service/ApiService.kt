package com.example.data.service

import com.example.data.dto.requestbody.LoginRequestBody
import com.example.data.dto.requestbody.SignUpRequestBody
import com.example.data.dto.responsebody.ResponseBody
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/user")
    suspend fun signUp(@Body signUpRequestBody: SignUpRequestBody)

    @POST("/user/login")
    suspend fun login(@Body loginRequestBody: LoginRequestBody) : Response<ResponseBody>
}