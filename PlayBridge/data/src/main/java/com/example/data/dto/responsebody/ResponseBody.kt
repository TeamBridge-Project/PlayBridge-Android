package com.example.data.dto.responsebody

import com.google.gson.annotations.SerializedName
import java.sql.Array

data class ResponseBody(
    @SerializedName("status") val status : Boolean,
    @SerializedName("result") val result : List<Unit>
)
