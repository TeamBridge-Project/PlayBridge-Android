package com.example.presentation.signup

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.R
import com.example.presentation.ui.common.LoadingIndicator
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.ComponentInnerColor
import com.example.presentation.ui.theme.PlayBridgeTheme
import com.example.presentation.ui.theme.SignUpCompleteColor
import com.example.presentation.ui.theme.notosanskr
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = BackgroundColor
                )
            }
            PlayBridgeTheme {
                Screen()
            }
        }
    }
}

@Composable
fun Screen(viewModel: SignUpViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ) {
        SignUpScreen(viewModel)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel
) {
    val activity = LocalContext.current as? Activity
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (nickName, setNickName) = remember { mutableStateOf("") }
    val (gender, setGender) = remember { mutableStateOf("") }
    val (birthday, setBirthday) = remember { mutableStateOf("") }
    val (isSmsChecked, setSmsCheck) = remember { mutableStateOf(false) }
    val (isEmailChecked, setEmailCheck) = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    if(viewModel.uiState.collectAsState().value == SignUpState.Loading){
        LoadingIndicator()
    }

    IconButton(
        onClick = { viewModel.backPress(activity) },
        modifier = Modifier.padding(top = 35.dp, start = 15.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
            contentDescription = "",
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp)
            .background(color = BackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = stringResource(id = R.string.sign_up),
            textAlign = TextAlign.Center,
            fontFamily = notosanskr,
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.padding(18.dp))

        InputComponent(
            textValue = email,
            textHint = stringResource(id = R.string.email),
            onValueChange = setEmail,
            keyboardController = keyboardController,
            passwordInput = false
        )

        Spacer(modifier = Modifier.padding(12.dp))

        InputComponent(
            textValue = password,
            textHint = stringResource(id = R.string.password),
            onValueChange = setPassword,
            keyboardController = keyboardController,
            passwordInput = true
        )

        Spacer(modifier = Modifier.padding(12.dp))

        InputComponent(
            textValue = nickName,
            textHint = stringResource(id = R.string.nickname),
            onValueChange = setNickName,
            keyboardController = keyboardController,
            passwordInput = false
        )

        Spacer(modifier = Modifier.padding(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GenderDropDown(keyboardController = keyboardController)

            BirthdayInput(
                textValue = birthday,
                onValueChange = setBirthday,
                keyboardController = keyboardController,
            )
        }

        Spacer(modifier = Modifier.padding(18.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.padding(start = 24.dp),
                text = stringResource(id = R.string.email_benefits_received),
                fontFamily = notosanskr,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(8.dp))
            CheckReceived(
                checked = isSmsChecked,
                onCheckedChanged = setSmsCheck,
                text = stringResource(id = R.string.sns_receive_agree)
            )
            Spacer(modifier = Modifier.padding(6.dp))
            CheckReceived(
                checked = isEmailChecked,
                onCheckedChanged = setEmailCheck,
                text = stringResource(id = R.string.email_receive_agree)
            )
        }

        Spacer(modifier = Modifier.padding(12.dp))

        Button(
            modifier = Modifier
                .shadow(elevation = 3.dp, shape = RoundedCornerShape(30.dp))
                .size(120.dp, 50.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(30.dp),
            onClick = {
                viewModel.signUp(
                    email,
                    password,
                    nickName,
                    gender,
                    birthday,
                    isSmsChecked,
                    isEmailChecked,
                    activity
                )
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = SignUpCompleteColor
            ),
        ) {
            Text(
                text = stringResource(id = R.string.complete),
                fontFamily = notosanskr,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

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

@OptIn(ExperimentalComposeUiApi::class)
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

@OptIn(ExperimentalComposeUiApi::class)
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

@OptIn(ExperimentalComposeUiApi::class)
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
