@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.presentation.start.component

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
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
import com.example.presentation.R
import com.example.presentation.ui.theme.ComponentInnerColor
import com.example.presentation.ui.theme.notosanskr

@Composable
internal fun LogInTextField(
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
internal fun LogInButton(
    onLogin: (String, String) -> Unit,
    email: String,
    password: String
) {
    Button(
        onClick = { onLogin(email, password) },
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
internal fun SignUpButton(
    moveSignUpPage: () -> Unit
) {
    val activity = LocalContext.current as? Activity
    TextButton(
        onClick = { moveSignUpPage() },
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

@Composable
internal fun LogoImage() {
    Image(
        painter = painterResource(id = R.drawable.logo_image),
        contentDescription = "",
        modifier = Modifier
            .padding(top = 20.dp)
            .width(350.dp)
    )
}
