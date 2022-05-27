package com.example.domain.usecase

import com.example.domain.model.user.ProfileUpdateModel
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        uuid: String,
        profileUpdateModel: ProfileUpdateModel
    ) = repository.updateProfile(uuid, profileUpdateModel)
}