package com.example.presentation.start

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.processMore
import com.example.domain.model.LoginModel
import com.example.domain.usecase.LoginUseCase
import com.example.presentation.main.MainActivity
import com.example.presentation.signup.SignUpActivity
import com.example.presentation.util.sha256
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StartViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    fun login(
        email: String,
        password: String,
        activity: Activity?
    ) {
        viewModelScope.launch {
            loginUseCase(LoginModel(email, password.sha256())).processMore(
                onSuccess = {
                    activity?.startActivity(Intent(activity, MainActivity::class.java))
                },
                onError = {

                }
            )

        }
    }

    fun moveSignUp(activity: Activity?) {
        activity?.startActivity(Intent(activity, SignUpActivity::class.java))
    }
}