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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val dataStore: DataStoreManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<StartState>(StartState.LoginNeeded)

    fun login(
        email: String,
        password: String,
        activity: Activity?
    ) {
        _uiState.value = StartState.Loading
        viewModelScope.launch {
            loginUseCase(LoginModel(email, password.sha256())).processMore(
                onSuccess = {
                    viewModelScope.launch {
                        dataStore.setAccessToken(it.accessToken)
                        dataStore.setRefreshToken(it.refreshToken)
                    }
                    activity?.startActivity(Intent(activity, MainActivity::class.java))
                    _uiState.value = StartState.Success
                },
            )
        }
    }

    fun moveSignUp(activity: Activity?) {
        activity?.startActivity(Intent(activity, SignUpActivity::class.java))
    }
}
