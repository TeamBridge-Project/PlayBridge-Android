package com.example.domain.repository

import com.example.domain.model.game.GameResponse
import com.skydoves.sandwich.ApiResponse

interface GameRepository {
    suspend fun getGameList(): ApiResponse<GameResponse>
}