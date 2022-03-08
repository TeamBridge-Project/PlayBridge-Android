package com.example.presentation.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.presentation.ui.theme.*

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SignUpScreen()
        }
    }
}

@Composable
fun Screen() {
    SignUpScreen()
}

@Composable
fun SignUpScreen() {
    val (id, setId) = remember { mutableStateOf("") }
    val (passWord,setPassWord) = remember { mutableStateOf("") }
    val (nickName,setNickName) = remember { mutableStateOf("") }
    val sex = remember { mutableStateOf("") }
    val birthDay = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "회원가입",
            textAlign = TextAlign.Center,
            fontFamily = notosanskr,
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        InputComponent(
            textValue = id,
            textHint = "아이디",
            onValueChange = setId,
            keyBordType = KeyboardType.Text
        )
        InputComponent(
            textValue = passWord,
            textHint = "비밀번호",
            onValueChange = setPassWord,
            keyBordType = KeyboardType.Password
        )
        InputComponent(
            textValue = nickName,
            textHint = "닉네임",
            onValueChange = setNickName,
            keyBordType = KeyboardType.Text
        )
    }
}

@Composable
fun InputComponent(
    textValue: String,
    textHint: String,
    onValueChange: (String) -> Unit,
    keyBordType: KeyboardType
) {
    OutlinedTextField(
        value = textValue,
        onValueChange = onValueChange,
        modifier = Modifier.shadow(elevation = 5.dp, shape = RoundedCornerShape(30.dp)),
        placeholder = {
            Text(
                text = textHint,
                fontFamily = notosanskr,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
        },
        shape = RoundedCornerShape(30.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = ComponentInnerColor,
            focusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyBordType)
    )
    Spacer(modifier = Modifier.height(16.dp))
}