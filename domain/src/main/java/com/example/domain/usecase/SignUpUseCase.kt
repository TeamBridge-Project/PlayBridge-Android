package com.example.domain.usecase

import com.example.domain.model.SignUpModel
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(signUpModel: SignUpModel) =
        repository.signUp(signUpModel)
}
