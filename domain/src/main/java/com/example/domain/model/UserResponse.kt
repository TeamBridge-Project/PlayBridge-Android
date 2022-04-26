package com.example.domain.model

data class UserResponse(
    val status: Boolean,
    val result: List<Unit>,
    val accessToken: String,
    val refreshToken: String
)
