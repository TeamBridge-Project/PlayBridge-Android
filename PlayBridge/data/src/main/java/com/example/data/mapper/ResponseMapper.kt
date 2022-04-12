package com.example.data.mapper

import com.example.data.dto.responsebody.ResponseBody
import com.example.domain.model.Response
import java.util.Collections.addAll

fun ResponseBody.toDomain() : Response = Response(
    status = status,
    result =  result.toList()
)