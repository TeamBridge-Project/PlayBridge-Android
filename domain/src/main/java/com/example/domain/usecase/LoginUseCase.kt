package com.example.domain.usecase

import com.example.domain.model.LoginModel
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(loginModel: LoginModel) =
        repository.login(loginModel)

}
