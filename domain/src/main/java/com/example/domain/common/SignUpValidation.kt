package com.example.domain.common

interface SignUpValidation {
    fun isEmailValidity(email: String) : Boolean

    fun isPasswordValidity(password: String) : Boolean

    fun isNickNameValidity(nickname: String) : Boolean

    fun isDateValidity(date: String) : Boolean
}