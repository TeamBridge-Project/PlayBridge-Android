package com.example.presentation.main.personalprofile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.main.common.DrawDot
import com.example.presentation.ui.theme.PointColor
import com.example.presentation.ui.theme.notosanskr

@Composable
fun Description(
    isEditing: MutableState<Boolean>,
    sectionName: String,
    registrationList: SnapshotStateList<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = sectionName,
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(userScrollEnabled = false) {
            itemsIndexed(registrationList) { index, _ ->
                Element(
                    isEditing = isEditing,
                    text = registrationList[index],
                    textChange = { registrationList[index] = it }
                )
            }
        }
    }
}

@Composable
fun Element(
    isEditing: MutableState<Boolean>,
    text: String,
    textChange: (String) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DrawDot(dotSize = 5, color = PointColor)

        BasicTextField(
            enabled = isEditing.value,
            modifier = Modifier
                .width(275.dp)
                .height(22.dp)
                .padding(start = 21.dp)
                .focusRequester(focusRequester),
            value = text,
            onValueChange = textChange,
            textStyle = TextStyle(
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal,
                fontFamily = notosanskr,
            ),
            singleLine = true,
            cursorBrush = SolidColor(Color.White),
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = { focusRequester.requestFocus() },
                enabled = isEditing.value,
                modifier = Modifier
                    .then(Modifier.size(24.dp))
                    .alpha(if (!isEditing.value) 0f else 1f),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_help_24),
                    contentDescription = "",
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(17.dp))
}