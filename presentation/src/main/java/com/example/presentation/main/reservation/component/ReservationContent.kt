package com.example.presentation.main.reservation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.ui.theme.notosanskr

@Composable
fun ReservationContent(
    textValue: String,
    contentText: String
) {
    Row(
        modifier = Modifier.width(300.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = textValue,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontFamily = notosanskr
        )

        Text(
            text = buildAnnotatedString {
                append(contentText)
                if(textValue == stringResource(id = R.string.reservation_fee)) {
                    append(" C")
                }
            },
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontFamily = notosanskr
        )
    }
}