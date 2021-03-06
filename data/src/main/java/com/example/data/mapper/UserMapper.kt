package com.example.data.mapper
import com.example.data.dto.requestbody.LoginRequestBody
import com.example.data.dto.requestbody.SignUpRequestBody
import com.example.domain.model.user.LoginModel
import com.example.domain.model.user.SignUpModel

fun SignUpModel.toData(): SignUpRequestBody = SignUpRequestBody(
    email = email,
    password = password,
    nickname = nickname,
    gender = gender,
    birthday = birthday,
    agreeEmail = agreeEmail
)

fun LoginModel.toData(): LoginRequestBody = LoginRequestBody(
    email = email,
    password = password
)
