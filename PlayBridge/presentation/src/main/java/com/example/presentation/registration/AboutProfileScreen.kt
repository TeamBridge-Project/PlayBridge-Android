package com.example.presentation.aboutprofile


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.ui.navigation.Screens
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.util.BackButton

import com.example.presentation.ui.util.RegistrationButton
import com.example.presentation.ui.util.Title

@Composable
fun AboutProfileScreen(navController : NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BackButton(navController = navController)
        Spacer(Modifier.height(60.dp))
        Title(stringResource(id = R.string.about_profile_title))
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        RegistrationButton(
            text = "등록",
            navController = navController,
            route = Screens.AboutProfileScreen.route)
    }
}