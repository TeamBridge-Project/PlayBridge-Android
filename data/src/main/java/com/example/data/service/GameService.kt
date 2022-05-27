package com.example.data.service

import com.example.domain.model.game.GameResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface GameService {
    @GET("/game")
    suspend fun getGameList(): ApiResponse<GameResponse>
}