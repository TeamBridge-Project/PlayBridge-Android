package com.example.presentation.start

sealed class StartState {
    object LoginNeeded : StartState()
    object Success : StartState()
    object Loading : StartState()
    object Failure : StartState()
}
