package com.example.domain.usecase

import com.example.domain.repository.UserRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(uuid: String) = repository.getProfile(uuid)

}
