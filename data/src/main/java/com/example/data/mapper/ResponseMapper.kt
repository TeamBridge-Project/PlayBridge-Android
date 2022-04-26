package com.example.data.mapper

import com.example.data.dto.responsebody.ResponseBody
import com.example.domain.model.UserResponse
import retrofit2.Response

object ResponseMapper {
    fun mapperResponse(response: Response<ResponseBody>): UserResponse {
        val headers = response.headers()
        val responseBody = checkNotNull(response.body())
        val accessToken = headers["X-Access-Token"] ?: ""
        val refreshToken = headers["X-Refresh-Token"] ?: ""
        return UserResponse(
            responseBody.status,
            responseBody.result.toList(),
            accessToken,
            refreshToken
        )
    }
}
