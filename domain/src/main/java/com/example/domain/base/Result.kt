package com.example.domain.base


sealed class Result<out T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Error<T>(val error: String, val message: String) : Result<T>()
}

fun <T> Result<T>.processMore(onSuccess: (T) -> Unit, onFailure: () -> Unit = {}): Result<T> {
    when (this) {
        is Result.Success -> {
            onSuccess(value)
        }
        else -> {
            onFailure()
        }
    }

    return this
}
