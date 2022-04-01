package com.example.presentation.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.presentation.R
import com.example.presentation.personalprofile.PersonalProfileScreen
import com.example.presentation.ui.navigation.Screens
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.notosanskr
import com.example.presentation.ui.util.BackButton
import com.example.presentation.ui.util.RegistrationButton
import com.example.presentation.ui.util.Title

@Preview(widthDp = 450, heightDp = 850)
@Composable
fun preview() {
    GameCostScreen(navController = rememberNavController())
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GameCostScreen(navController : NavController) {
    val (gameCost,setGameCost) = remember { mutableStateOf("") }
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
        Spacer(Modifier.height(30.dp))
        CostInput(
            gameCost = gameCost,
            setGameCost = setGameCost,
            keyboardController = keyboardController)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            RegistrationButton(
                text = "다음",
                navController = navController,
                route = Screens.AboutProfileScreen.route)
        }
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
    ) {
        Box(
            modifier = Modifier
                .padding(top = 2.dp, end = 10.dp)
        ){
            Text(
                text = "1시간당/",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = notosanskr,
                color = Color.White,
            )
        }

        OutlinedTextField(
            value = gameCost,
            onValueChange = setGameCost,
            modifier = Modifier
                .width(120.dp)
                .height(40.dp),
            textStyle = TextStyle(
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Medium,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()} ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                focusedIndicatorColor = Color.White
            ),
        )
    }
}