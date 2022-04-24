package com.example.domain.usecase

import com.example.domain.base.BaseUseCase
import com.example.domain.base.Result
import com.example.domain.model.LoginModel
import com.example.domain.repository.UserRepository
import java.lang.IllegalStateException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UserRepository
): BaseUseCase() {
    suspend operator fun invoke(loginModel: LoginModel) = execute {
        repository.login(loginModel)
    }

}
