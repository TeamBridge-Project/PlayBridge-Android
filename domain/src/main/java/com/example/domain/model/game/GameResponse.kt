package com.example.domain.model.game

import com.example.domain.model.ErrorModel

data class GameResponse(
    val result: List<GameModel>,
    val errors: List<ErrorModel>,
)
