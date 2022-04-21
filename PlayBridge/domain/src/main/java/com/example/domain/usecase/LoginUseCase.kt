package com.example.domain.usecase

import com.example.domain.base.Result
import com.example.domain.model.LoginModel
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UserRepository
) : BaseUseCase() {
    suspend operator fun invoke(loginModel: LoginModel) =
        withContext(Dispatchers.IO) {
            val response = repository.login(loginModel)
            if (response.status) {
                Result.Success(response)
            } else {
                Result.Error(Exception())
            }
        }
}
