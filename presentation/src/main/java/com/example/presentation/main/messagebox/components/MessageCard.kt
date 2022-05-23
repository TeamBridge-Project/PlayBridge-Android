package com.example.presentation.main.messagebox.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.ui.theme.MaleBadgeColor
import com.example.presentation.ui.theme.MessageCardColor
import com.example.presentation.ui.theme.SignUpCompleteColor
import com.example.presentation.ui.theme.notosanskr

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MessageCard(
    profileImage: Painter,
    nickName: String,
    gender: String,
) {
    Card(
        modifier = Modifier
            .padding(top = 32.dp)
            .width(320.dp)
            .height(80.dp),
        shape = RoundedCornerShape(30.dp),
        backgroundColor = MessageCardColor,
        onClick = { }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 21.dp, end = 7.dp)
                    .size(40.dp)
                    .clip(CircleShape),
                painter = profileImage,
                contentDescription = " "
            )

            Column {
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold,fontSize = 12.sp))
                        { append(nickName) }
                        withStyle(SpanStyle(fontWeight = FontWeight.Light,fontSize = 9.sp))
                        { append("님") }
                    },
                    fontFamily = notosanskr,
                    color = Color.White
                )

                MessageCardBadge(gender = gender)
            }

            Spacer(modifier = Modifier.width(173.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
                contentDescription = ""
            )
        }
    }
}

@Composable
internal fun MessageCardBadge(
    gender: String
) {
    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .size(16.dp)
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
            fontSize = 8.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White
        )
    }
}
