package com.example.domain.model.user

import com.example.domain.model.ErrorModel

data class UserResponse(
    val result: List<UserModel>,
    val errors: List<ErrorModel>,
)
