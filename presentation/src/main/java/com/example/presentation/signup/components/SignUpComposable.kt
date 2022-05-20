@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.presentation.signup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.presentation.R
import com.example.presentation.ui.theme.ComponentInnerColor
import com.example.presentation.ui.theme.notosanskr

@Composable
fun CheckReceived(
    checked: Boolean,
    onCheckedChanged: (Boolean) -> Unit,
    text: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            modifier = Modifier.padding(start = 16.dp),
            checked = checked,
            onCheckedChange = onCheckedChanged,
            colors = CheckboxDefaults.colors(
                uncheckedColor = Color.White,
            )
        )

        Text(
            text = text,
            fontFamily = notosanskr,
            fontSize = 12.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun InputComponent(
    textValue: String,
    textHint: String,
    onValueChange: (String) -> Unit,
    keyboardController: SoftwareKeyboardController?,
    passwordInput: Boolean
) {
    Box {
        BasicTextField(
            visualTransformation = if (passwordInput) {
                PasswordVisualTransformation(mask = '\u002A')
            } else {
                VisualTransformation.None
            },
            modifier = Modifier
                .width(282.dp)
                .height(50.dp)
                .clip(shape = RoundedCornerShape(30.dp)),
            value = textValue,
            cursorBrush = SolidColor(Color.White),
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontFamily = notosanskr,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(ComponentInnerColor)
                        .padding(start = 20.dp, end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    innerTextField()
                }
            },
        )
        if (textValue.isEmpty()) {
            Text(
                modifier = Modifier.offset(20.dp, 15.dp),
                text = textHint,
                fontFamily = notosanskr,
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun BirthdayInput(
    textValue: String,
    onValueChange: (String) -> Unit,
    keyboardController: SoftwareKeyboardController?,
) {
    Box {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(50.dp)
                .clip(shape = RoundedCornerShape(30.dp)),
            value = textValue,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontFamily = notosanskr,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(ComponentInnerColor)
                        .padding(start = 20.dp, end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    innerTextField()
                }
            },
        )
        if (textValue.isEmpty()) {
            Text(
                modifier = Modifier.offset(20.dp, 15.dp),
                text = stringResource(id = R.string.birthday),
                fontFamily = notosanskr,
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun GenderDropDown(
    keyboardController: SoftwareKeyboardController?,
) {
    var expanded by remember { mutableStateOf(false) }
    val genderList = listOf("남", "여")
    var selectedGender by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val dropDownIcon = if (expanded) {
        R.drawable.ic_baseline_keyboard_arrow_up_24
    } else {
        R.drawable.ic_baseline_keyboard_arrow_down_24
    }

    Box {
        BasicTextField(
            enabled = false,
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .height(50.dp)
                .clip(shape = RoundedCornerShape(30.dp))
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            value = selectedGender,
            onValueChange = { selectedGender = it },
            textStyle = TextStyle(
                fontFamily = notosanskr,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(ComponentInnerColor)
                        .padding(start = 20.dp, end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    innerTextField()
                }
            },
        )

        IconButton(
            onClick = {},
            modifier = Modifier
                .offset(50.dp, 2.dp)
        ) {
            Icon(
                painter = painterResource(id = dropDownIcon),
                contentDescription = "",
                modifier = Modifier.clickable { expanded = !expanded },
                tint = Color.White
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
        ) {
            genderList.forEach { selection ->
                DropdownMenuItem(
                    onClick = {
                        selectedGender = selection
                        expanded = false
                    },
                ) {
                    Text(text = selection)
                }
            }
        }

        if (selectedGender.isEmpty()) {
            Text(
                modifier = Modifier.offset(20.dp, 15.dp),
                text = stringResource(id = R.string.sex),
                fontFamily = notosanskr,
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
