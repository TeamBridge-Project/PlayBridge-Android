@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.presentation.start

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.R
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.ComponentInnerColor
import com.example.presentation.ui.theme.notosanskr
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
    StartScreen(viewModel = viewModel)

}

@Composable
fun LoadingIndicator(){
    Dialog(
        onDismissRequest = {},
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(
            contentAlignment= Alignment.Center,
            modifier = Modifier
        ) {
            CircularProgressIndicator( color = White )
        }
    }
}

@Composable
fun StartScreen(
    viewModel: StartViewModel
) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val activity = LocalContext.current as? Activity
    val keyboardController = LocalSoftwareKeyboardController.current

    if(viewModel.uiState.collectAsState().value == StartState.Loading){
        LoadingIndicator()
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
        LogInButton(activity, viewModel::login, email, password)
        Spacer(modifier = Modifier.height(25.dp))
        Divider(Modifier.width(380.dp), Color.Gray)
        SignUpButton(activity, viewModel::moveSignUp)
    }
}

@Composable
fun LogoImage() {
    Image(
        painter = painterResource(id = R.drawable.logo_image),
        contentDescription = "",
        modifier = Modifier
            .padding(top = 20.dp)
            .width(350.dp)
    )
}

@Composable
fun LogInTextField(
    hintValue: String,
    textValue: String,
    onTextChange: (String) -> Unit,
    passwordInput: Boolean,
    keyboardController: SoftwareKeyboardController?
) {
    Box {
        BasicTextField(
            visualTransformation = if (passwordInput) {
                PasswordVisualTransformation(mask = '\u002A')
            } else {
                VisualTransformation.None
            },
            modifier = Modifier
                .width(350.dp)
                .height(50.dp)
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(30.dp)
                )
                .clip(shape = RoundedCornerShape(30.dp)),
            value = textValue,
            cursorBrush = SolidColor(Color.White),
            onValueChange = onTextChange,
            textStyle = TextStyle(
                fontFamily = notosanskr,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(ComponentInnerColor)
                        .padding(start = 28.dp, end = 28.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    innerTextField()
                }
            },
        )
        if (textValue.isEmpty()) {
            Text(
                modifier = Modifier.offset(29.dp, 12.dp),
                text = hintValue,
                fontFamily = notosanskr,
                color = Color.Gray,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun LogInButton(
    activity: Activity?,
    onLogin: (String, String, Activity?) -> Unit,
    email: String,
    password: String
) {
    Button(
        onClick = { onLogin(email, password, activity) },
        modifier = Modifier
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
}

@Composable
fun SignUpButton(
    activity: Activity?,
    moveSignUpPage: (Activity?) -> Unit
) {
    TextButton(
        onClick = { moveSignUpPage(activity) },
        modifier = Modifier
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
