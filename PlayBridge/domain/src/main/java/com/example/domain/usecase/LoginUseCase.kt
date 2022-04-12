package com.example.domain.usecase

import com.example.domain.model.LoginModel
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(loginModel: LoginModel) =
        withContext(Dispatchers.IO){
            repository.login(loginModel)
        }

}
