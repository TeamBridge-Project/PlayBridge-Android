package com.example.presentation.aboutprofile


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource

import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.ui.navigation.Screens
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.util.BackButton

import com.example.presentation.ui.util.RegistrationButton
import com.example.presentation.ui.util.Title
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

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
        Spacer(Modifier.height(60.dp))
        ProfileSection()
        Spacer(Modifier.height(60.dp))
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

@Composable
fun ProfileSection(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 60.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(){
            GlideImage(
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)
                    .clip(CircleShape)
                    .clickable { },
                imageModel = ImageBitmap.imageResource(R.drawable.profile_image),
                circularReveal = CircularReveal(duration = 250),
                error = ImageBitmap.imageResource(id = R.drawable.profile_image)
            )
        }

        Spacer(modifier = Modifier.width(20.dp))
        Text(text = "프로필 사진", color = Color.White)
    }
}