package com.example.presentation.signup

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val dataStore: DataStoreManager
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
        activity: Activity?
    ) {
        _uiState.value = SignUpState.Loading

        if (!email.matches("""^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[.][a-zA-Z]{2,3}$""".toRegex())) {
            _uiState.value = SignUpState.EmailFailed
        } else if(!password.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,20}$".toRegex())) {
            _uiState.value = SignUpState.PasswordFailed
        } else if(!nickname.matches("^[가-힣a-zA-Z0-9]{2,6}$".toRegex())) {
            _uiState.value = SignUpState.NickName
        } else if(!date.matches("^(19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$".toRegex())) {
            _uiState.value = SignUpState.Date
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
                    activity?.startActivity(Intent(activity, MainActivity::class.java))
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

    fun backPress(activity: Activity?) {
        activity?.finish()
    }
}
