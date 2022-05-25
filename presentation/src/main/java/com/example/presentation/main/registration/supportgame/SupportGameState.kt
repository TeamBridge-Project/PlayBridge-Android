package com.example.presentation.main.registration.supportgame

import com.example.domain.model.game.GameModel
import com.example.presentation.ui.common.UiStatus

data class SupportGameState(
    val status: UiStatus = UiStatus.Loading,
    val gameList: List<GameModel> = emptyList()
)
