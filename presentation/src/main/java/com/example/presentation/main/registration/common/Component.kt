package com.example.presentation.main.registration.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.ui.theme.ProfileEditingColor
import com.example.presentation.ui.theme.notosanskr

@Composable
internal fun BackButton(onBackPressed: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(
            onClick = { onBackPressed() },
            modifier = Modifier.padding(top = 15.dp, start = 15.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                contentDescription = "",
            )
        }
    }
}

@Composable
internal fun Title(text: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 60.dp)) {
        Text(
            text = text,
            fontSize = 35.sp,
            color = Color.White,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
internal fun RegistrationButton(
    text: String,
    movePage: () -> Unit
) {
    Button(
        onClick = { movePage() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ProfileEditingColor
        ),
        modifier = Modifier
            .clip(shape = RoundedCornerShape(30.dp))
            .width(100.dp)
            .height(40.dp)
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(30.dp)),
        contentPadding = PaddingValues(0.dp),
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            color = Color.White,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Bold
        )
    }
}
