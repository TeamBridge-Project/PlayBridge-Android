package com.example.domain.model

import java.util.*

data class SignUpModel(
    val email : String,
    val password : String,
    val nickname : String,
    val gender : String,
    val birthday : Date,
    val agreeSms : Boolean,
    val agreeEmail : Boolean
)
