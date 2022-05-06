package com.example.presentation.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.ui.navigation.HomeScreens
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.notosanskr
import com.example.presentation.ui.util.BackButton
import com.example.presentation.ui.util.RegistrationButton
import com.example.presentation.ui.util.Title

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GameCostScreen(navController: NavController) {
    val (gameCost, setGameCost) = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BackButton(navController = navController)
        Spacer(Modifier.height(60.dp))
        Title(stringResource(id = R.string.game_cost_title))
        Spacer(Modifier.height(100.dp))
        GameName(selectedGameName = "리그오브레전드")
        Spacer(Modifier.height(60.dp))
        CostInput(
            gameCost = gameCost,
            setGameCost = setGameCost,
            keyboardController = keyboardController
        )
        Spacer(modifier = Modifier.height(300.dp))
        RegistrationButton(
            text = "다음",
            navController = navController,
            route = HomeScreens.AboutProfileScreen.route
        )
    }
}

@Composable
fun GameName(
    selectedGameName: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 60.dp),
    ) {
        BasicTextField(
            value = selectedGameName,
            onValueChange = {},
            readOnly = true,
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = notosanskr,
                color = Color.White,
            )
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CostInput(
    gameCost: String,
    setGameCost: (String) -> Unit,
    keyboardController: SoftwareKeyboardController?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "1시간당 / ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = notosanskr,
            color = Color.White,
        )

        Box {
            Column() {
                Row() {
                    BasicTextField(
                        modifier = Modifier.width(180.dp),
                        value = gameCost,
                        onValueChange = setGameCost,
                        maxLines = 1,
                        textStyle = TextStyle(
                            fontFamily = notosanskr,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            keyboardController?.hide()
                        }),
                        cursorBrush = SolidColor(Color.White),
                    )
                    Text(
                        text = " C",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Divider(modifier = Modifier.width(200.dp).height(3.dp), color = Color.White)
            }
        }
    }
}