package com.example.presentation.main.personalprofile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.main.personalprofile.common.PencilIcon
import com.example.presentation.ui.theme.SelfIntroduction
import com.example.presentation.ui.theme.notosanskr

@Composable
fun IntroSection(
    isEditing: MutableState<Boolean>
) {
    val (selfIntroduction, setSelfIntroduction) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "자기소개",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.width(8.dp))

            PencilIcon(isEditing = isEditing)
        }

        Spacer(modifier = Modifier.height(17.dp))

        Row(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
        ) {
            TextField(
                readOnly = !isEditing.value,
                modifier = Modifier
                    .width(331.dp)
                    .height(115.dp)
                    .clip(shape = RoundedCornerShape(15.dp)),
                textStyle = TextStyle(
                    fontSize = 12.sp,
                    color = Color.White,
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Bold
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = SelfIntroduction
                ),
                value = selfIntroduction,
                onValueChange = setSelfIntroduction,
            )
        }
    }
}