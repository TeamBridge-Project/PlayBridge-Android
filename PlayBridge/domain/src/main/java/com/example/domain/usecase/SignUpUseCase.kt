package com.example.domain.usecase

import com.example.domain.base.Result
import com.example.domain.base.BaseUseCase
import com.example.domain.model.SignUpModel
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: UserRepository
) : BaseUseCase() {
    suspend operator fun invoke(signUpModel: SignUpModel) =
        withContext(Dispatchers.IO){
            val response = repository.signUp(signUpModel)
            if(response.status){
                Result.Success(response)
            }else{
                Result.Error(Exception())
            }
        }
}