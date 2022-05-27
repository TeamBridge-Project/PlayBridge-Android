package com.example.presentation.main.personalprofile.common

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.ui.common.noRippleClickable
import com.example.presentation.ui.navigation.HomeScreens
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun Backward(
    navController: NavController
) {
    CoilImage(
        modifier = Modifier
            .size(24.dp)
            .noRippleClickable { navController.navigate(HomeScreens.HomeScreen.route) }
            .then(Modifier.size(24.dp)),
        imageModel = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
        error = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
    )
}
