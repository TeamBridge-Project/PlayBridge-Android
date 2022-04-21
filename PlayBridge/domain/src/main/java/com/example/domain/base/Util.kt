package com.example.domain.base

sealed class Result<out T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Error<T>(val error: Throwable) : Result<T>()
}

fun <T> Result<T>.processMore(onSuccess: (T) -> Unit, onError: () -> Unit = {}): Result<T> {
    when (this) {
        is Result.Success -> {
            onSuccess(value)
        }
        is Result.Error -> {
            onError()
        }
    }

    return this
}

abstract class BaseUseCase {
    protected inline fun <T> execute(block: () -> T): Result<T> = runCatching {
        Result.Success(block())
    }.getOrElse {
        it.printStackTrace()
        Result.Error(it)
    }
}
