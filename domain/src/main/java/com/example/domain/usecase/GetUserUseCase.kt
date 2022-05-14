package com.example.domain.usecase

import com.example.domain.base.BaseUseCase
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
): BaseUseCase() {
    suspend operator fun invoke(query: Int) = runCatching {
        repository.getUser(query)
    }
}