package com.example.data.dto.requestbody

import com.google.gson.annotations.SerializedName
import java.util.Date

data class SignUpRequestBody(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("birthday") val birthday: Date,
    @SerializedName("agreeEmail") val agreeEmail: Boolean

)
