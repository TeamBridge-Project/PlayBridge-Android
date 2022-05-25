package com.example.presentation.main.personalprofile.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.ui.theme.ProfileEditingColor
import com.example.presentation.ui.theme.notosanskr

@Composable
fun ProfileEditButton(
    isEditing: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { isEditing.value = !isEditing.value },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ProfileEditingColor
            ),
            modifier = Modifier
                .clip(shape = RoundedCornerShape(30.dp))
                .width(101.dp)
                .height(40.dp),
            contentPadding = PaddingValues(0.dp),
        ) {
            Text(
                if (!isEditing.value) "편집" else "완료",
                fontSize = 15.sp,
                color = Color.White,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Bold
            )
        }
    }
}