package com.example.presentation.start

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.processMore
import com.example.domain.model.LoginModel
import com.example.domain.usecase.LoginUseCase
import com.example.local.datastore.DataStoreManager
import com.example.presentation.main.MainActivity
import com.example.presentation.signup.SignUpActivity
import com.example.presentation.util.sha256
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.onSuccess
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
        viewModelScope.launch {
            loginUseCase(LoginModel(email, password.sha256())).suspendOnSuccess{
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
    }

    fun moveSignUp(activity: Activity?) {
        activity?.startActivity(Intent(activity, SignUpActivity::class.java))
    }
}
