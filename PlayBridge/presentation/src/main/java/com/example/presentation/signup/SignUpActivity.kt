package com.example.presentation.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
    val (passWord, setPassWord) = remember { mutableStateOf("") }
    val (nickName, setNickName) = remember { mutableStateOf("") }
    val (sex, setSex) = remember { mutableStateOf("") }
    val (birthday, setBirthday) = remember { mutableStateOf("") }
    val (isSmsChecked, setSmsCheck) = remember { mutableStateOf(false) }
    val (isEmailChecked, setEmailCheck) = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = BackgroundColor)
            .padding(54.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "회원가입",
            textAlign = TextAlign.Center,
            fontFamily = notosanskr,
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.padding(24.dp))

        InputComponent(
            textValue = id,
            textHint = "아이디",
            onValueChange = setId,
            keyBordType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.padding(12.dp))

        InputComponent(
            textValue = passWord,
            textHint = "비밀번호",
            onValueChange = setPassWord,
            keyBordType = KeyboardType.Password
        )

        Spacer(modifier = Modifier.padding(12.dp))

        InputComponent(
            textValue = nickName,
            textHint = "닉네임",
            onValueChange = setNickName,
            keyBordType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.padding(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RowComponent(
                textValue = sex,
                textHint = "성별",
                onValueChange = setSex,
                keyBordType = KeyboardType.Text,
                ratio = 0.35f
            )
            RowComponent(
                textValue = birthday,
                textHint = "생일",
                onValueChange = setBirthday,
                keyBordType = KeyboardType.Text,
                ratio = 0.9f
            )
        }

        Spacer(modifier = Modifier.padding(24.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.padding(start = 24.dp),
                text = "이메일/혜택 수신 여부",
                fontFamily = notosanskr,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(8.dp))
            CheckRecieve(
                checked = isSmsChecked,
                onCheckedChanged = setSmsCheck,
                text = "SMS 수신 동의"
            )
            Spacer(modifier = Modifier.padding(6.dp))
            CheckRecieve(
                checked = isEmailChecked,
                onCheckedChanged = setEmailCheck,
                text = "E-mail 수신 동의"
            )
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Button(
            modifier = Modifier
                .shadow(elevation = 3.dp, shape = RoundedCornerShape(30.dp))
                .size(150.dp, 50.dp),
            shape = RoundedCornerShape(30.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = SignUpCompleteColor
            ),
        ) {
            Text(
                text = "완료",
                fontFamily = notosanskr,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Composable
fun CheckRecieve(
    checked: Boolean,
    onCheckedChanged: (Boolean) -> Unit,
    text: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            modifier = Modifier.padding(start = 16.dp),
            checked = checked,
            onCheckedChange = onCheckedChanged,
            colors = CheckboxDefaults.colors(
                uncheckedColor = Color.White,
            )
        )
        Text(
            text = text,
            fontFamily = notosanskr,
            color = Color.White,
            fontWeight = FontWeight.Bold
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
}

@Composable
fun RowComponent(
    textValue: String,
    textHint: String,
    onValueChange: (String) -> Unit,
    keyBordType: KeyboardType,
    ratio: Float
) {
    OutlinedTextField(
        value = textValue,
        onValueChange = onValueChange,
        modifier = Modifier
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(30.dp))
            .fillMaxWidth(ratio),
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

}