package com.example.domain.base

import com.example.domain.model.UserResponse

abstract class BaseUseCase {
    protected inline fun <T> execute(block: () -> T): Result<T> = runCatching {
        val response = block()
        if(response is UserResponse){
            if(response.status){
                Result.Success(response)
            }else{
                Result.Failure()
            }
        }else{
            Result.Success(block())
        }
    }.getOrElse {
        it.printStackTrace()
        Result.Error(it)
    }
}