package com.example.domain.common

interface LoginValidator {
    fun isEmailValidity(email: String) : Boolean

    fun isPasswordValidity(email: String) : Boolean
}