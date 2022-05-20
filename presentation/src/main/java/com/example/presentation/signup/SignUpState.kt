package com.example.presentation.signup

sealed class SignUpState{
    object SignUpNeeded : SignUpState()
    object Success : SignUpState()
    object Loading : SignUpState()
    object EmailFailed : SignUpState()
    object PasswordFailed : SignUpState()
    object NickName : SignUpState()
    object Date : SignUpState()
}
