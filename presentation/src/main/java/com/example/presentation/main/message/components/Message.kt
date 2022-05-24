package com.example.presentation.main.message.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.ui.theme.notosanskr

@Composable
fun Message() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "받은 쪽지",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = notosanskr,
                color = Color.Cyan
            )

            Text(
                text = buildAnnotatedString {
                    append("21/09/18")
                    append(" ")
                    append("23:04")
                },
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = notosanskr,
                color = Color.Gray
            )
        }

        BasicTextField(
            value = "안녕하세요 감사해요 잘있어요 다시 만나요 매일 같은 하루",
            onValueChange = { },
            readOnly = true,
            modifier = Modifier.padding(10.dp),
            textStyle = TextStyle(
                color = Color.White,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Normal
            )
        )
    }

    Divider(modifier = Modifier.fillMaxWidth(), color = Color.Gray)
}
