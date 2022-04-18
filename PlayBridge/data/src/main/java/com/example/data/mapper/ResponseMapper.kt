package com.example.data.mapper

import com.example.data.dto.responsebody.ResponseBody
import com.example.domain.model.UserResponse
import retrofit2.Response

object Mapper {
    fun mapperResponse(response: Response<ResponseBody>): UserResponse {
        val headers = response.headers()
        val accessToken = headers["X-Access-Token"] ?: ""
        val refreshToken = headers["X-Refresh-Token"] ?: ""
        return UserResponse(
            response.body()!!.status,
            response.body()!!.result.toList(),
            accessToken,
            refreshToken
        )
    }

}
