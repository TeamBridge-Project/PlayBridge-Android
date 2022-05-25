package com.example.presentation.main.personalprofile.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.ui.navigation.HomeScreens

@Composable
fun Backward(
    navController: NavController
) {
    IconButton(
        onClick = { navController.navigate(HomeScreens.HomeScreen.route) },
        modifier = Modifier.then(Modifier.size(24.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
            contentDescription = "",
        )
    }
}
