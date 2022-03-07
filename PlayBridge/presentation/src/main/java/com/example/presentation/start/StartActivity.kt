package com.example.presentation.start

import android.os.Bundle
import android.os.PersistableBundle
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
import androidx.core.view.WindowCompat
import com.example.presentation.R
import com.example.presentation.ui.theme.Background
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
            .background(color = Background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Play",
            fontSize = 75.sp,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            //textAlign = TextAlign.Left,
            modifier = Modifier.height(160.dp)
        )
        Box(modifier = Modifier.fillMaxSize(), Alignment.TopEnd) {
            Text(
                text = "bridge",
                fontSize = 75.sp,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                //textAlign = TextAlign.Right,
                //modifier = Modifier.height(300.dp)
            )
        }
        //Spacer(modifier = Modifier.height(50.dp))

    }
}