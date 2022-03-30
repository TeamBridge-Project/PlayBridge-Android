package com.example.data.dto.requestbody

import com.google.gson.annotations.SerializedName
import java.util.*

data class SignUpRequestBody(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("birthday") val birthday: Date,
    @SerializedName("agreeSms") val agreeSms: Boolean,
    @SerializedName("agreeEmail") val agreeEmail: Boolean

)
