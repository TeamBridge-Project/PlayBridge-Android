package com.example.domain.model

data class AuthResponse(
    val result: List<AuthModel>,
    val errors: List<ErrorModel>,
) {
    data class AuthModel(
        val uuid: String
    )
}