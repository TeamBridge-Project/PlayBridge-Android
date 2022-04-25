package com.example.presentation.main

import android.os.Bundle
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
}
