@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.presentation.main.registration.gamecost

import android.widget.Toast
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
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.ui.navigation.HomeScreens
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.notosanskr
import com.example.presentation.main.registration.common.BackButton
import com.example.presentation.main.registration.common.RegistrationButton
import com.example.presentation.main.registration.common.Title

@Composable
fun GameCostScreen(
    navController: NavController,
    game: String,
    tier: String,
) {
    val (gameCost, setGameCost) = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor),
    ) {
        BackButton { navController.popBackStack() }
        Spacer(Modifier.height(60.dp))
        Title(stringResource(id = R.string.game_cost_title))
        Spacer(Modifier.height(100.dp))
        GameName(selectedGameName = game)
        Spacer(Modifier.height(60.dp))
        CostInput(
            gameCost = gameCost,
            setGameCost = setGameCost,
            keyboardController = keyboardController,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            RegistrationButton("다음") {
                navController.navigate(
                    HomeScreens.AboutProfileScreen.getDestination(
                        game,
                        tier,
                        gameCost.toInt()
                    )
                )
            }
        }
    }
}

@Composable
internal fun GameName(
    selectedGameName: String
) {
    Row(
        modifier = Modifier
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

@Composable
internal fun CostInput(
    gameCost: String,
    setGameCost: (String) -> Unit,
    keyboardController: SoftwareKeyboardController?
) {
    val context = LocalContext.current
    val maxLength = 9

    Row(
        modifier = Modifier.padding(start = 60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.per_hour),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = notosanskr,
            color = Color.White,
        )

        Box(modifier = Modifier.padding(start = 5.dp)) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BasicTextField(
                        modifier = Modifier.width(180.dp),
                        value = gameCost,
                        onValueChange = {
                            if (it.length <= maxLength) {
                                setGameCost(it)
                            } else {
                                Toast.makeText(context, "최대 9자리 까지 입력이 가능합니다.", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        },
                        maxLines = 1,
                        singleLine = true,
                        textStyle = LocalTextStyle.current.copy(
                            fontFamily = notosanskr,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End
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
                        modifier = Modifier.padding(start = 3.dp, top = 1.dp),
                        text = stringResource(id = R.string.coin_icon),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Divider(
                    modifier = Modifier
                        .width(200.dp)
                        .height(3.dp),
                    color = Color.White
                )
            }
        }
    }
}