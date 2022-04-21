package com.example.data.mapper
import com.example.data.dto.requestbody.LoginRequestBody
import com.example.data.dto.requestbody.SignUpRequestBody
import com.example.domain.model.LoginModel
import com.example.domain.model.SignUpModel

fun SignUpModel.toData(): SignUpRequestBody = SignUpRequestBody(
    email = email,
    password = password,
    nickname = nickname,
    gender = gender,
    birthday = birthday,
    agreeSms = agreeSms,
    agreeEmail = agreeEmail
)

fun LoginModel.toData(): LoginRequestBody = LoginRequestBody(
    email = email,
    password = password
)
