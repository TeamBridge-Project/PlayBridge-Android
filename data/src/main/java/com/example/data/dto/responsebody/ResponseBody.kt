package com.example.data.dto.responsebody

import com.google.gson.annotations.SerializedName

data class ResponseBody(
    @SerializedName("status") val status: Boolean,
    @SerializedName("result") val result: List<Unit>
)
