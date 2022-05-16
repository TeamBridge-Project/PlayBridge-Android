package com.example.domain.model

data class UserResponse(
    val result: List<UserModel>,
    val errors: List<ErrorModel>,
)
