package com.example.presentation.main.message

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.ProfileEditingColor
import com.example.presentation.ui.theme.notosanskr

@Preview
@Composable
internal fun SendMessageScreen() {
    val (content,setContent) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 5.dp, end = 15.dp, top = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_close_24),
                    contentDescription = " "
                )
            }

            Text(
                text = stringResource(id = R.string.send_message),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = notosanskr,
                color = Color.White
            )

            Button(
                onClick = { },
                modifier = Modifier
                    .width(60.dp)
                    .height(30.dp),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = ProfileEditingColor)
            ) {
                Text(
                    text = stringResource(id = R.string.send),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = notosanskr,
                    color = Color.White
                )
            }
        }

        OutlinedTextField(
            value = content,
            onValueChange = setContent,
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            textStyle = TextStyle(
                fontFamily = notosanskr,
                fontWeight = FontWeight.Medium,
                color = Color.White
            ),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.content_input),
                    fontFamily = notosanskr,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.Transparent
            )
        )
    }
}