package com.example.presentation.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.core.view.WindowCompat
import com.example.presentation.main.MainActivity
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.PlayBridgeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            PlayBridgeTheme {
                val systemUiController = rememberSystemUiController()

                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = BackgroundColor
                    )
                }

                SignUpScreen()
            }
        }
    }

    internal fun startMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
