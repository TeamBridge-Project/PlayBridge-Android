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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.ComponentInnerColor
import com.example.presentation.ui.theme.ProfileEditingColor
import com.example.presentation.ui.theme.notosanskr

@Composable
fun SupportGameScreen(navController: NavController) {
    val gameList = listOf("리그 오브 레전드", "배틀 그라운드", "로스트아크", "메이플")
    val tierList = listOf("골드 IV", "골드 III", "골드 II", "골드 I","실버 IV","실버 III","실버 II", "실버 I")
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
        NextButton()
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
fun Title(text:String) {
    Row(Modifier.fillMaxWidth().padding(start = 60.dp)){
        Text(
            text = text,
            fontSize = 35.sp,
            color = Color.White,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Bold
        )
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownComponent(
    optionList: List<String>,
    placeHolderText: String
) {
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
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textFieldSize = coordinates.size.toSize()
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
            optionList.forEach { selection ->
                DropdownMenuItem(
                    onClick = {
                        selectedText = selection
                        expanded = !expanded
                    },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Column() {
                        Text(
                            text = selection,
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

@Composable
fun NextButton(){
    Button(
        onClick = {},

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
            text = "편집",
            fontSize = 15.sp,
            color = Color.White,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Bold
        )
    }
}

