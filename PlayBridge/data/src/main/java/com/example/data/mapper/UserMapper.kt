package com.example.data.mapper

import android.service.autofill.UserData
import com.example.data.dto.requestbody.SignUpRequestBody
import com.example.domain.model.SignUpModel
import java.util.*

fun SignUpModel.toData() : SignUpRequestBody = SignUpRequestBody(
    email = email,
    password = password,
    nickname = nickname,
    gender = gender,
    birthday = birthday,
    agreeSms = agreeSms,
    agreeEmail = agreeEmail
)