package com.example.domain.model.user

import java.util.Date

data class SignUpModel(
    val email: String,
    val password: String,
    val nickname: String,
    val gender: String,
    val birthday: Date,
    val agreeEmail: Boolean
)
