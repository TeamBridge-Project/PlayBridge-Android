package com.example.presentation.main.personalprofile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.ui.common.noRippleClickable
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.notosanskr
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun CoinSection(
    coin: String,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundColor),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.coin_mark))
                append(" ")
                append(coin)
            },
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .noRippleClickable { }
        )

        Spacer(modifier = Modifier.width(10.dp))

        CoilImage(
            modifier = Modifier
                .size(23.dp)
                .clip(CircleShape)
                .noRippleClickable { },
            imageModel = painterResource(id = R.drawable.question_icon),
            circularReveal = CircularReveal(duration = 250),
            error = painterResource(id = R.drawable.question_icon),
        )
    }
}