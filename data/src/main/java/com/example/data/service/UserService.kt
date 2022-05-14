package com.example.data.service

import com.example.data.dto.requestbody.LoginRequestBody
import com.example.data.dto.requestbody.SignUpRequestBody
import com.example.data.dto.responsebody.ResponseBody
import com.example.data.dto.responsebody.UserResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("/user")
    suspend fun signUp(@Body signUpRequestBody: SignUpRequestBody): Response<ResponseBody>

    @POST("/user/login")
    suspend fun login(@Body loginRequestBody: LoginRequestBody): Response<ResponseBody>

    @GET("/user")
    suspend fun getUserList(@Query("page") page: Int): Response<UserResponseBody>
}
