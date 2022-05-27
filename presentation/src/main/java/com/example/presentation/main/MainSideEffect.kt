package com.example.presentation.main

sealed class MainSideEffect{
    data class NavigateToRegistration(val uuid: String) : MainSideEffect()
}