package com.example.data.service

import com.example.data.dto.requestbody.LoginRequestBody
import com.example.data.dto.requestbody.PlayingGamesRequestBody
import com.example.data.dto.requestbody.SignUpRequestBody
import com.example.domain.model.game.OriginResponse
import com.example.domain.model.game.PlayingGameModel
import com.example.domain.model.user.AuthResponse
import com.example.domain.model.user.ProfileUpdateModel
import com.example.domain.model.user.UserResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @POST("/user")
    suspend fun signUp(@Body signUpRequestBody: SignUpRequestBody): ApiResponse<AuthResponse>

    @POST("/user/login")
    suspend fun login(@Body loginRequestBody: LoginRequestBody): ApiResponse<AuthResponse>

    @GET("/user")
    suspend fun getUserList(@Query("page") page: Int): Response<UserResponse>

    @POST("/user/{uuid}/profile")
    suspend fun getProfile(@Path("uuid") uuid: String): ApiResponse<UserResponse>

    @POST("/user/profile/{uuid}/update")
    suspend fun updateProfile(
        @Path("uuid") uuid: String,
        @Body profileUpdateModel: ProfileUpdateModel
    ): ApiResponse<UserResponse>

    @POST("/user/addplayinggames")
    suspend fun addPlayingGames(
        @Body playingGamesRequestBody : PlayingGamesRequestBody,
    ): ApiResponse<OriginResponse>
}
