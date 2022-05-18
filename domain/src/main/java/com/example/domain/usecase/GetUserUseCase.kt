package com.example.domain.usecase

import com.example.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(query: Int) = runCatching {
        repository.getUser(query)
    }
}
