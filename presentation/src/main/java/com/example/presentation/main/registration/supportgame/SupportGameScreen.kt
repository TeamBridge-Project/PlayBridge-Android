package com.example.presentation.main.registration.supportgame

import android.util.Log
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
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.ComponentInnerColor
import com.example.presentation.ui.theme.notosanskr
import com.example.presentation.main.registration.common.BackButton
import com.example.presentation.main.registration.common.RegistrationButton
import com.example.presentation.main.registration.common.Title
import com.example.presentation.ui.common.LoadingIndicator
import com.example.presentation.ui.common.UiStatus
import com.example.presentation.ui.navigation.HomeScreens

@Composable
fun SupportGameScreen(
    viewModel: SupportGameViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    navController: NavController,
) {
    val (gameText, setGameText) = remember { mutableStateOf("") }
    val (tierText, setTierText) = remember { mutableStateOf("") }
    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                is SupportGameSideEffect.NavigateToGameCostScreen -> {
                    navController.navigate(
                        HomeScreens.GameCostScreen.getDestination(it.game, it.tier)
                    )
                }
            }
        }
    }

    val state by viewModel.container.stateFlow.collectAsState()

    when (state.status) {
        UiStatus.Success -> {
            val gameList = state.gameList.map { it.name }
            val tierList =
                listOf("?????? IV", "?????? III", "?????? II", "?????? I", "?????? IV", "?????? III", "?????? II", "?????? I")
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = BackgroundColor),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                BackButton(onBackPressed)
                Spacer(Modifier.height(60.dp))
                Title(stringResource(id = R.string.support_game_title))
                Spacer(Modifier.height(50.dp))
                DropDownComponent(
                    optionList = gameList,
                    placeHolderText = stringResource(id = R.string.game_select_or_edit),
                    gameText,
                    setGameText
                )
                Spacer(Modifier.height(40.dp))
                DropDownComponent(
                    optionList = tierList,
                    placeHolderText = stringResource(id = R.string.rank_or_level_edit),
                    tierText,
                    setTierText
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 60.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                RegistrationButton("??????") { viewModel.onRegister(gameText, tierText) }
            }
        }
        UiStatus.Loading -> {
            LoadingIndicator()
        }
        else -> {}
    }

}

@Composable
internal fun DropDownComponent(
    optionList: List<String>,
    placeHolderText: String,
    text: String,
    setText: (String) -> Unit,
) {
    var itemCount: Int = 0
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown
    Column {
        OutlinedTextField(
            value = text,
            onValueChange = setText,
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
                        setText(selection)
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