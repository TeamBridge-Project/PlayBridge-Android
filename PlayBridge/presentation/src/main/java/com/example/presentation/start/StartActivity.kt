package com.example.presentation.start

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.R

import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.ComponentInnerColor
import com.example.presentation.ui.theme.notosanskr
import com.example.presentation.util.datastore.StartViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = BackgroundColor
                )
            }
            Screen()
        }
    }

}

@Composable
fun Screen(viewModel: StartViewModel = hiltViewModel()) {
    StartScreen(viewModel::login, viewModel::moveSignUp)
}


@Composable
fun TextComponent(
    hintValue: String,
    textValue: String,
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .width(350.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(30.dp)
            ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = ComponentInnerColor,
            focusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ),
        placeholder = {
            Text(
                text = hintValue,
                fontFamily = notosanskr,
                fontSize = 17.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
        },
        shape = RoundedCornerShape(30.dp),
        value = textValue,
        onValueChange = onTextChange,
        maxLines = 1,
        singleLine = true,
    )
}

@Composable
fun StartScreen(
    onLogin: (String, String, Activity?) -> Unit,
    moveSignUpPage: (Activity?) -> Unit,
) {
    val (email, setEmail) = remember {
        mutableStateOf("")
    }
    val (password, setPassword) = remember {
        mutableStateOf("")
    }
    val activity = LocalContext.current as? Activity
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_image),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 20.dp)
                .width(350.dp)
        )

        TextComponent(stringResource(id = R.string.login_page_id), email, setEmail)

        Spacer(modifier = Modifier.height(20.dp))

        TextComponent(stringResource(id = R.string.login_page_password), password, setPassword)

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                onLogin(email, password, activity)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(350.dp)
                .height(50.dp)
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(16.dp),
                    clip = true
                ),
            colors = ButtonDefaults.buttonColors(backgroundColor = ComponentInnerColor),
            shape = RoundedCornerShape(50),
        ) {
            Text(
                text = stringResource(id = R.string.login_page_login),
                fontFamily = notosanskr,
                fontSize = 17.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Divider(
            color = Color.Gray,
            modifier = Modifier.width(380.dp)
        )

        TextButton(
            onClick = { moveSignUpPage(activity) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.login_page_sign_up),
                fontFamily = notosanskr,
                fontSize = 17.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}