package com.example.presentation.signup

sealed class SignUpState{
    object SignUpNeeded : SignUpState()
    object Success : SignUpState()
    object Loading : SignUpState()
}