package com.example.presentation.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.ui.navigation.HomeScreens
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.ComponentInnerColor
import com.example.presentation.ui.theme.notosanskr
import com.example.presentation.ui.util.BackButton
import com.example.presentation.ui.util.RegistrationButton
import com.example.presentation.ui.util.Title

@Composable
fun SupportGameScreen(navController: NavController) {
    val gameList = listOf("리그 오브 레전드", "배틀 그라운드", "로스트아크", "메이플")
    val tierList = listOf("골드 IV", "골드 III", "골드 II", "골드 I", "실버 IV", "실버 III", "실버 II", "실버 I")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BackButton(navController = navController)
        Spacer(Modifier.height(60.dp))
        Title(stringResource(id = R.string.support_game_title))
        Spacer(Modifier.height(50.dp))
        DropDownComponent(
            optionList = gameList,
            placeHolderText = stringResource(id = R.string.game_select_or_edit)
        )
        Spacer(Modifier.height(40.dp))
        DropDownComponent(
            optionList = tierList,
            placeHolderText = stringResource(id = R.string.rank_or_level_edit)
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        RegistrationButton("다음", navController, HomeScreens.GameCostScreen.route)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownComponent(
    optionList: List<String>,
    placeHolderText: String
) {
    var itemCount: Int = 0
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown
    Column() {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier.onGloballyPositioned { coordinates ->
                textFieldSize = coordinates.size.toSize()
            },
            textStyle = TextStyle(
                fontFamily = notosanskr,
                fontWeight = FontWeight.Medium,
                color = Color.White
            ),
            placeholder = {
                Text(
                    text = placeHolderText,
                    fontFamily = notosanskr,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
            },
            trailingIcon = {
                Icon(
                    icon,
                    "",
                    Modifier.clickable { expanded = !expanded },
                    tint = Color.White
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = ComponentInnerColor,
                cursorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                focusedIndicatorColor = Color.White
            ),
        )
        DropdownMenu(
            modifier = Modifier
                .background(ComponentInnerColor)
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            optionList.forEach { selection ->
                DropdownMenuItem(
                    onClick = {
                        selectedText = selection
                        expanded = !expanded
                    },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
                ) {
                    Text(
                        text = selection,
                        color = Color.White
                    )
                }

                itemCount++

                if (optionList.size != itemCount) {
                    Divider(thickness = 2.dp)
                }
            }
        }
    }
}
