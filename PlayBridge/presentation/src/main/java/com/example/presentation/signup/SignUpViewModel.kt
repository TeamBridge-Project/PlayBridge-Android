package com.example.presentation.signup


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.SignUpModel
import com.example.domain.usecase.SignUpUseCase
import com.example.presentation.util.sha256
import com.example.presentation.util.toDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {
    fun signUp(
        email: String, password: String, nickname: String,
        gender: String, date: String, agreeSms: Boolean, agreeEmail: Boolean
    ) {
        viewModelScope.launch {
            signUpUseCase(
                SignUpModel(
                    email, password.sha256(), nickname, gender,
                    date.toDate(), agreeSms, agreeEmail
                )

            )
        }
    }
}