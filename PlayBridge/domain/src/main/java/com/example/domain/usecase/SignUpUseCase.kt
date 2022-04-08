package com.example.domain.usecase


import com.example.domain.model.SignUpModel
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(signUpModel: SignUpModel) =
        withContext(Dispatchers.IO){
            repository.signUp(signUpModel)
        }
}