package com.example.data.dto.requestbody

import com.google.gson.annotations.SerializedName

data class PlayingGamesRequestBody(
    @SerializedName("id") val id: String,
    @SerializedName("tier") val tier: String,
    @SerializedName("tierLevel") val tierLevel: Int,
    @SerializedName("feePerHour") val feePerHour: Int,
    @SerializedName("feePerPlay") val feePerPlay: Int,
    @SerializedName("uuid") val uuid: String
)