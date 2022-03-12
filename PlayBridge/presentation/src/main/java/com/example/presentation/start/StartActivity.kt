package com.example.presentation.start

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.presentation.R
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.ComponentInnerColor
import com.example.presentation.ui.theme.notosanskr
import java.time.format.TextStyle

class StartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            StartScreen()
        }
    }

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

@Preview(showBackground = true, widthDp = 400, heightDp = 700)
@Composable
fun StartScreen() {
    var (id, setId) = remember {
        mutableStateOf("")
    }
    var (password, setPassword) = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_image),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 70.dp)
                .width(350.dp)
        )

        TextComponent("ID", id, setId)

        Spacer(modifier = Modifier.height(20.dp))

        TextComponent("Password", password, setPassword)

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {},
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
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = "Login",
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
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
        ) {
            Text(
                text = "sign up",
                fontFamily = notosanskr,
                fontSize = 17.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}