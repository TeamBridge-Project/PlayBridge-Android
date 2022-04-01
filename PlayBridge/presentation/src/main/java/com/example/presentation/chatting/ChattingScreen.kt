package com.example.presentation.chatting

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.presentation.R
import com.example.presentation.main.Gender
import com.example.presentation.personalprofile.PersonalProfileScreen
import com.example.presentation.ui.theme.*
import kotlinx.coroutines.NonDisposableHandle.parent


@Preview(widthDp = 450, heightDp = 850)
@Composable
fun preview() {
    ChattingScreen(navController = rememberNavController())
}

@Composable
fun ChattingScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .background(BackgroundColor)
            .wrapContentSize(Alignment.Center),
    ) {
        ChattingBox()
    }
}

@Composable
fun ChattingBox() {
    var (inputText,setInputText) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .width(366.dp)
            .height(743.dp)
            .background(ChattingBoxColor)
            .clip(shape = RoundedCornerShape(20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopSectionButton()
        Spacer(modifier = Modifier.height(30.dp))
        Profile(
            profileImage = R.drawable.ic_baseline_account_circle_24,
            stateCircle = R.drawable.ic_baseline_circle_24,
            nickname = "둥글둥글",
            gender = "남",
            stateText = "현재 접속 중"
        )
        Spacer(modifier = Modifier.height(32.dp))
        ChattingWindow()
        ChattingInputField(inputText = inputText, setInputText = setInputText)
    }
}

@Composable
fun ChattingInputField(
    inputText: String,
    setInputText: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .width(366.dp)
            .height(48.dp)
            .clip(shape = RoundedCornerShape(15.dp)),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        value = inputText,
        onValueChange = setInputText
    )
}

@Composable
fun ChattingWindow() {
    Column(
        modifier = Modifier
            .height(437.dp)
            .width(345.dp)
    ) {

    }
}

@Composable
fun Profile(
    profileImage: Int,
    stateCircle: Int,
    nickname: String,
    gender: String,
    stateText: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box() {
            Image(
                painter = painterResource(id = profileImage),
                contentDescription = ""
            )
            Box(
                modifier = Modifier
                    .align(BottomEnd)
                    .offset(-15.dp, -12.dp),
            ) {
                Image(
                    painter = painterResource(id = stateCircle),
                    contentDescription = ""
                )
            }
        }
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = nickname,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = notosanskr
            )
            Text(
                modifier = Modifier.padding(bottom = 2.dp, end = 4.dp),
                text = "님",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = notosanskr
            )
            Row(
                modifier = Modifier.padding(bottom = 4.dp)
            ){
                Gender(textValue = gender)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stateText,
            color = Color.White,
            fontSize = 8.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = notosanskr
        )
    }
}

@Composable
fun TopSectionButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { },
            modifier = Modifier.padding(end = 169.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                contentDescription = "",
            )
        }

        RightButton(buttonName = "프로필 보기",endPadding = 10.dp)
        RightButton(buttonName = "스케줄 확인",endPadding = 16.dp)
    }
}

@Composable
fun RightButton(
    buttonName: String,
    endPadding: Dp,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(end = endPadding)
    ) {
        Button(
            onClick = { },
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .width(64.dp)
                .height(26.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ProfileEditingColor
            )
        ) {
            Text(
                text = buttonName,
                fontSize = 8.sp,
                color = Color.White,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Bold
            )
        }
    }
}