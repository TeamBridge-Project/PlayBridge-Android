@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.presentation.start

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.R
import com.example.presentation.signup.SignUpActivity
import com.example.presentation.start.component.LogInButton
import com.example.presentation.start.component.LogInTextField
import com.example.presentation.start.component.LogoImage
import com.example.presentation.start.component.SignUpButton
import com.example.presentation.ui.common.LoadingIndicator
import com.example.presentation.ui.theme.BackgroundColor


@Composable
internal fun StartScreen(viewModel: StartViewModel = hiltViewModel()) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val activity = LocalContext.current as? StartActivity
    val keyboardController = LocalSoftwareKeyboardController.current

    when (val startUiState = viewModel.uiState.collectAsState().value) {
        StartState.Loading ->
            LoadingIndicator()
        StartState.Success -> {
            activity?.startMain()
        }
        StartState.Failed -> {
            LaunchedEffect(startUiState) {
                Toast.makeText(activity, "????????? ?????? ??????????????? ????????????.", Toast.LENGTH_SHORT).show()
                viewModel.changeStateLoginNeeded()
            }
        }
        else -> {}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LogoImage()

        LogInTextField(
            stringResource(id = R.string.login_page_id),
            email,
            setEmail,
            false,
            keyboardController
        )

        Spacer(modifier = Modifier.height(20.dp))

        LogInTextField(
            stringResource(id = R.string.login_page_password),
            password,
            setPassword,
            true,
            keyboardController
        )
        Spacer(modifier = Modifier.height(20.dp))

        LogInButton(viewModel::login, email, password)

        Spacer(modifier = Modifier.height(25.dp))

        Divider(Modifier.width(380.dp), Color.Gray)

        SignUpButton { activity?.startActivity(Intent(activity, SignUpActivity::class.java)) }

    }
}