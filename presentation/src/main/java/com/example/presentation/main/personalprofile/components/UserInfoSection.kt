package com.example.presentation.main.personalprofile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.main.common.GenderBadge
import com.example.presentation.ui.theme.notosanskr

@Composable
fun UserInfoSection(
    profileImage: Painter,
    nickname: String,
    gender: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { }
        ) {
            Image(
                painter = profileImage,
                contentDescription = "",
            )
        }

        Spacer(modifier = Modifier.width(24.dp))

        Column {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)) {
                        append(nickname)
                    }

                    append(stringResource(id = R.string.nim))
                },
                color = Color.White,
                fontSize = 15.sp,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Light
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row { GenderBadge(gender = gender, badgeSize = 24.dp, textSize = 12.sp) }
        }
    }
}