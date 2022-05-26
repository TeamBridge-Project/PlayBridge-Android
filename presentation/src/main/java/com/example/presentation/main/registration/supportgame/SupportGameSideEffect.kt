package com.example.presentation.main.registration.supportgame

sealed class SupportGameSideEffect{
    data class NavigateToGameCostScreen(val game: String, val tier: String) : SupportGameSideEffect()
}