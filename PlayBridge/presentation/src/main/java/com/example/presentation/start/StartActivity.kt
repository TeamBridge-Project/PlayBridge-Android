package com.example.presentation.start

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.ui.theme.notosanskr

class StartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            .background(Color(R.color.background_color))
    ) {
        Text(
            text = "Play",
            fontSize = 75.sp,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Bold)
        Text(
            text = "bridge",
            fontSize = 75.sp,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 1.dp)
        )


    }
}