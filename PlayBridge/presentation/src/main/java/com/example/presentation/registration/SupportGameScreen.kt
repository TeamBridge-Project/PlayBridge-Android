package com.example.presentation.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.ComponentInnerColor
import com.example.presentation.ui.theme.notosanskr

@Composable
fun SupportGameScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BackButton(navController = navController)
        Spacer(Modifier.height(60.dp))
        Title()
        Spacer(Modifier.height(60.dp))
        GameList()
    }
}

@Composable
fun BackButton(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(top = 35.dp, start = 15.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                contentDescription = "",
            )
        }
    }
}

@Composable
fun Title() {
    Text(
        text = stringResource(id = R.string.support_game_title),
        fontSize = 35.sp,
        color = Color.White,
        fontFamily = notosanskr,
        fontWeight = FontWeight.Bold
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameList() {
    val gameList = listOf("리그 오브 레전드", "배틀 그라운드", "로스트아크", "메이플")
    var expanded by remember { mutableStateOf(false) }
    var selectedGameText by remember { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown
    Column() {
        OutlinedTextField(
            value = selectedGameText,
            onValueChange = { selectedGameText = it },
            modifier = Modifier.onGloballyPositioned { coordinates ->
                textfieldSize = coordinates.size.toSize()
            },
            textStyle = TextStyle(
                fontFamily = notosanskr,
                fontWeight = FontWeight.Medium,
                color = Color.White
            ),
            placeholder = {
                Text(
                    text = "게임 선택/작성",
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
                .shadow(elevation = 2.dp)
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }) {
            Divider(
                color = BackgroundColor,
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp
            )
            gameList.forEach { selectionGame ->
                DropdownMenuItem(
                    onClick = {
                        selectedGameText = selectionGame
                    },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textfieldSize.width.toDp() }),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Column() {
                        Text(
                            text = selectionGame,
                            modifier = Modifier.padding(12.dp),
                            color = Color.White
                        )
                        Divider(
                            color = BackgroundColor,
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 2.dp
                        )
                    }

                }

            }
        }
    }

}