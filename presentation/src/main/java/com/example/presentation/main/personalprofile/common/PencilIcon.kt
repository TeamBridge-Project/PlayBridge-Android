package com.example.presentation.main.personalprofile.common

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun PencilIcon(isEditing: MutableState<Boolean>) {
    CoilImage(
        modifier = Modifier
            .size(22.dp)
            .clip(CircleShape)
            .alpha(if (!isEditing.value) 0f else 1f),
        imageModel = painterResource(id = R.drawable.pencil_icon),
        circularReveal = CircularReveal(duration = 250),
        error = painterResource(id = R.drawable.pencil_icon),
    )
}