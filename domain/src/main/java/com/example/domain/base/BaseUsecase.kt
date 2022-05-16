package com.example.domain.base

import com.example.domain.model.UserResponse

/*
abstract class BaseUseCase {
    protected inline fun execute(block: () -> UserResponse): Result<UserResponse>  {
        val result = block()
        if(result.errors.isEmpty()){
            return Result.Success(result)
        }else{
            with(result.errors[0]){
                return Result.Error(error,message)
            }
        }
    }
}
*/
