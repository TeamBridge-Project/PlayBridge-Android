package com.example.presentation.start

sealed class StartState {
    object LoginNeeded : StartState()
    object Success : StartState()
    object Failed : StartState()
    object Loading : StartState()
}
