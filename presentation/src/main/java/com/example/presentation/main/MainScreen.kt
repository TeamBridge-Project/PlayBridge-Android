package com.example.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.presentation.main.component.FunctionSelectBar
import com.example.presentation.main.component.TopBar
import com.example.presentation.ui.theme.BackgroundColor

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    Spacer(modifier = Modifier.height(60.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        TopBar(navController = navController)
        Spacer(modifier = Modifier.height(30.dp))
        FunctionSelectBar(navController = navController, viewModel = viewModel)
    }
}
