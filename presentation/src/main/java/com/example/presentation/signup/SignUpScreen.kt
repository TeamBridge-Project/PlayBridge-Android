package com.example.presentation.signup

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.R
import com.example.presentation.signup.components.BirthdayInput
import com.example.presentation.signup.components.CheckReceived
import com.example.presentation.signup.components.GenderDropDown
import com.example.presentation.signup.components.InputComponent
import com.example.presentation.ui.common.LoadingIndicator
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.SignUpCompleteColor
import com.example.presentation.ui.theme.notosanskr

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ) {
        val activity = LocalContext.current as? Activity
        val (email, setEmail) = remember { mutableStateOf("") }
        val (password, setPassword) = remember { mutableStateOf("") }
        val (nickName, setNickName) = remember { mutableStateOf("") }
        val gender = remember { mutableStateOf("") }
        val (birthday, setBirthday) = remember { mutableStateOf("") }
        val (isEmailChecked, setEmailCheck) = remember { mutableStateOf(false) }
        val keyboardController = LocalSoftwareKeyboardController.current
        val signUpUiState = viewModel.uiState.collectAsState().value

        if (signUpUiState == SignUpState.Loading) {
            LoadingIndicator()
        }

        LaunchedEffect(signUpUiState) {
            when (signUpUiState) {
                SignUpState.EmailFailed -> {
                    Toast.makeText(activity, "이메일 형식이 틀렸습니다.", Toast.LENGTH_SHORT).show()
                }
                SignUpState.PasswordFailed -> {
                    Toast.makeText(activity, "패스워드 형식이 틀렸습니다.", Toast.LENGTH_SHORT).show()
                }
                SignUpState.NickNameFailed -> {
                    Toast.makeText(activity, "닉네임 형식이 틀렸습니다.", Toast.LENGTH_SHORT).show()
                }
                SignUpState.DateFailed -> {
                    Toast.makeText(activity, "생일 형식이 틀렸습니다.", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
            viewModel.changeStateSignUpNeeded()
        }

        IconButton(
            onClick = { viewModel.backPress(activity) },
            modifier = Modifier.padding(top = 35.dp, start = 15.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                contentDescription = "",
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp)
                .background(color = BackgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = stringResource(id = R.string.sign_up),
                textAlign = TextAlign.Center,
                fontFamily = notosanskr,
                fontSize = 40.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.padding(18.dp))

            InputComponent(
                textValue = email,
                textHint = stringResource(id = R.string.email),
                onValueChange = setEmail,
                keyboardController = keyboardController,
                passwordInput = false
            )

            Spacer(modifier = Modifier.padding(12.dp))

            InputComponent(
                textValue = password,
                textHint = stringResource(id = R.string.password),
                onValueChange = setPassword,
                keyboardController = keyboardController,
                passwordInput = true
            )

            Spacer(modifier = Modifier.padding(12.dp))

            InputComponent(
                textValue = nickName,
                textHint = stringResource(id = R.string.nickname),
                onValueChange = setNickName,
                keyboardController = keyboardController,
                passwordInput = false
            )

            Spacer(modifier = Modifier.padding(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),

                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                GenderDropDown(keyboardController = keyboardController)

                BirthdayInput(
                    textValue = birthday,
                    onValueChange = setBirthday,
                    keyboardController = keyboardController,
                )
            }

            Spacer(modifier = Modifier.padding(18.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.padding(start = 24.dp),
                    text = stringResource(id = R.string.email_benefits_received),
                    fontFamily = notosanskr,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.padding(8.dp))

                CheckReceived(
                    checked = isEmailChecked,
                    onCheckedChanged = setEmailCheck,
                    text = stringResource(id = R.string.email_receive_agree)
                )
            }

            Spacer(modifier = Modifier.padding(12.dp))

            Button(
                modifier = Modifier
                    .shadow(elevation = 3.dp, shape = RoundedCornerShape(30.dp))
                    .size(120.dp, 50.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(30.dp),
                onClick = {
                    viewModel.signUp(
                        email,
                        password,
                        nickName,
                        gender.value,
                        birthday,
                        isEmailChecked,
                        activity
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = SignUpCompleteColor
                ),
            ) {
                Text(
                    text = stringResource(id = R.string.complete),
                    fontFamily = notosanskr,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
