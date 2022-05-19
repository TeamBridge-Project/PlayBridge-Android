package com.example.presentation.start

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.LoginModel
import com.example.domain.usecase.LoginUseCase
import com.example.local.datastore.DataStoreManager
import com.example.presentation.main.MainActivity
import com.example.presentation.signup.SignUpActivity
import com.example.presentation.util.sha256
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val dataStore: DataStoreManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<StartState>(StartState.LoginNeeded)
    val uiState: StateFlow<StartState> = _uiState

    fun login(
        email: String,
        password: String,
        activity: Activity?
    ) {
        _uiState.value = StartState.Loading
        if(email.matches("""^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[.][a-zA-Z]{2,3}$""".toRegex())
            and password.matches("""^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,20}$""".toRegex())) {
            viewModelScope.launch {
                loginUseCase(LoginModel(email, password.sha256())).suspendOnSuccess {
                    val accessToken = headers["X-Access-Token"]!!
                    val refreshToken = headers["X-Refresh-Token"]!!
                    dataStore.setAccessToken(accessToken)
                    dataStore.setRefreshToken(refreshToken)
                    activity?.startActivity(Intent(activity, MainActivity::class.java))
                    _uiState.value = StartState.Success
                }.onFailure {
                    _uiState.value = StartState.LoginNeeded
                }
            }
        } else {
            _uiState.value = StartState.Failed
        }
    }

    fun changeStateLoginNeeded() {
        _uiState.value = StartState.LoginNeeded
    }

    fun moveSignUp(activity: Activity?) {
        activity?.startActivity(Intent(activity, SignUpActivity::class.java))
    }
}
