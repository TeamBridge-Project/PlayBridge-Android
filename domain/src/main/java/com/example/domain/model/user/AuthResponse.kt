package com.example.domain.model.user

import com.example.domain.model.ErrorModel

data class AuthResponse(
    val result: List<AuthModel>,
    val errors: List<ErrorModel>,
) {
    data class AuthModel(
        val uuid: String
    )
}