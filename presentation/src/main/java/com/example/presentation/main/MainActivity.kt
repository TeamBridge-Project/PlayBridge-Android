package com.example.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import com.example.presentation.ui.navigation.Navigation
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.PlayBridgeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var backKeyPressedTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = BackgroundColor
                )
            }

            PlayBridgeTheme() {
                Navigation()
            }
        }
    }

    override fun onBackPressed() {
        val nowTime = System.currentTimeMillis()

        if (nowTime - backKeyPressedTime < 2000) {
            finish();
        } else {
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
            backKeyPressedTime = System.currentTimeMillis()
        }
    }
}
