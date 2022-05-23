package com.example.presentation.signup

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.SignUpValidator
import com.example.domain.model.SignUpModel
import com.example.domain.usecase.SignUpUseCase
import com.example.local.datastore.DataStoreManager
import com.example.presentation.main.MainActivity
import com.example.presentation.util.sha256
import com.example.presentation.util.toDate
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val dataStore: DataStoreManager,
    private val signUpValidator: SignUpValidator
) : ViewModel() {

    private val _uiState = MutableStateFlow<SignUpState>(SignUpState.SignUpNeeded)
    val uiState: MutableStateFlow<SignUpState> = _uiState

    fun signUp(
        email: String,
        password: String,
        nickname: String,
        gender: String,
        date: String,
        agreeEmail: Boolean,
    ) {
        _uiState.value = SignUpState.Loading

        if (!signUpValidator.isEmailValidity(email)) {
            _uiState.value = SignUpState.EmailFailed
        } else if (!signUpValidator.isPasswordValidity(password)) {
            _uiState.value = SignUpState.PasswordFailed
        } else if (!signUpValidator.isNickNameValidity(nickname)) {
            _uiState.value = SignUpState.NickNameFailed
        } else if (!signUpValidator.isDateValidity(date)) {
            _uiState.value = SignUpState.DateFailed
        } else {
            val charGender = when (gender) {
                "남" -> "m"
                else -> {
                    "f"
                }
            }
            viewModelScope.launch {
                signUpUseCase(
                    SignUpModel(
                        email, password.sha256(), nickname, charGender,
                        date.toDate(), agreeEmail
                    )
                ).suspendOnSuccess {
                    val accessToken = headers["X-Access-Token"]!!
                    val refreshToken = headers["X-Refresh-Token"]!!
                    dataStore.setAccessToken(accessToken)
                    dataStore.setRefreshToken(refreshToken)
                    _uiState.value = SignUpState.Success
                }.onFailure {
                    _uiState.value = SignUpState.SignUpNeeded
                }
            }
        }
    }

    fun changeStateSignUpNeeded() {
        _uiState.value = SignUpState.SignUpNeeded
    }
}
