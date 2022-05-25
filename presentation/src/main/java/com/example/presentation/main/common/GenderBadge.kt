package com.example.presentation.main.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.ui.theme.MaleBadgeColor
import com.example.presentation.ui.theme.SignUpCompleteColor
import com.example.presentation.ui.theme.notosanskr

@Composable
internal fun GenderBadge(
    gender: String,
    badgeSize: Dp,
    textSize: TextUnit
) {
    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .size(badgeSize)
            .clip(CircleShape)
            .background(
                if (gender == "m") {
                    MaleBadgeColor
                } else {
                    SignUpCompleteColor
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = buildAnnotatedString {
                if (gender == "m") {
                    append("남")
                } else if (gender == "f") {
                    append("여")
                }
            },
            fontFamily = notosanskr,
            fontSize = textSize,
            fontWeight = FontWeight.Normal,
            color = Color.White
        )
    }
}