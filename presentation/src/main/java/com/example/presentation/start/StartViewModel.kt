package com.example.presentation.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.LoginValidator
import com.example.domain.model.user.LoginModel
import com.example.domain.usecase.LoginUseCase
import com.example.local.datastore.DataStoreManager
import com.example.presentation.util.sha256
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val dataStore: DataStoreManager,
    private val loginValidator: LoginValidator
) : ViewModel() {

    private val _uiState = MutableStateFlow<StartState>(StartState.LoginNeeded)
    val uiState: StateFlow<StartState> = _uiState

    fun login(
        email: String,
        password: String,
    ) {
        _uiState.value = StartState.Loading
        if (loginValidator.isEmailValidity(email)
            and loginValidator.isPasswordValidity(password)
        ) {
            viewModelScope.launch {
                loginUseCase(LoginModel(email, password.sha256())).suspendOnSuccess {
                    val accessToken = headers["X-Access-Token"]!!
                    val refreshToken = headers["X-Refresh-Token"]!!
                    val uuid = data.result[0].uuid
                    dataStore.setAccessToken(accessToken)
                    dataStore.setRefreshToken(refreshToken)
                    dataStore.setUuid(uuid)
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
}
