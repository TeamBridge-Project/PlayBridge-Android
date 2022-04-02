package com.example.presentation.signup


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.SignUpModel
import com.example.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase ) : ViewModel() {
    fun signUp() {
        viewModelScope.launch {
            signUpUseCase(
                SignUpModel("dd@dsd", "22", "aa", "m", Date(123), true, false)
            )
        }
    }
}