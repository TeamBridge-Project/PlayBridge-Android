package com.example.presentation.start

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.ui.theme.Background
import com.example.presentation.ui.theme.notosanskr

class StartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val window: Window = this.window
            window.navigationBarColor = Background.toArgb()
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
            .background(color = Background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row() {
            Text(
                text = "Play",
                fontSize = 75.sp,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Left,
                modifier = Modifier.wrapContentHeight(Alignment.Bottom)
            )
        }
        Image(
            painter =
                painterResource(
                    id = R.drawable.ic_baseline_square_foot_24),
                    contentDescription = ""
        )
        Text(
            text = "bridge",
            fontSize = 75.sp,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Bold,
            //modifier = Modifier.padding(bottom = 200.dp),
            color = Color.White
        )
    }
}