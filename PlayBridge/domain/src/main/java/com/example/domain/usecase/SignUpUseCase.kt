package com.example.domain.usecase


import com.example.domain.base.BaseUseCase
import com.example.domain.model.SignUpModel
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: UserRepository
): BaseUseCase() {
    suspend operator fun invoke(signUpModel: SignUpModel) = execute {
        repository.signUp(signUpModel)
    }
}
