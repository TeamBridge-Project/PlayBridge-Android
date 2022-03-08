package com.example.presentation.start

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.presentation.R
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.ComponentInnerColor
import com.example.presentation.ui.theme.notosanskr

class StartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            StartScreen()
        }
    }

}

@Preview(showBackground = true, widthDp = 400, heightDp = 700)
@Composable
fun StartScreen() {
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
                .padding(top = 100.dp)
                .width(350.dp)
        )

        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 60.dp)
                .width(131.dp)
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
                text = "로그인",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }

        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(30.dp)
                .width(131.dp)
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
                text = "회원가입",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 0.dp)
            )
        }
    }
}