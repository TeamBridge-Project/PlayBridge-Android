package com.example.presentation.personalprofile


import android.provider.ContactsContract
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.presentation.R
import com.example.presentation.ui.theme.*
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage


@Preview(widthDp = 450, heightDp = 850)
@Composable
fun preview() {
    PersonalProfileScreen(navController = rememberNavController())
}

@Composable
fun PersonalProfileScreen(navController: NavController) {
    val isEditing = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        CoinSection(quantity = 3000)
        ProfilePersonalInformationSection(
            profileImage = painterResource(id = R.drawable.ic_baseline_account_circle_24),
            nickname = stringResource(id = R.string.dungledungle),
            userRating = stringResource(id = R.string.new_rating),
            gender =  stringResource(id = R.string.male)
        )
        Spacer(modifier = Modifier.height(27.dp))
        SellerRegistrationGameSection(isEditing = isEditing)
        RegisteredGameFeeSection(isEditing = isEditing)
        SelfIntroductionSection(isEditing = isEditing)
        ProfileEditButton(isEditing = isEditing)
    }
    Backward(navController = navController)
}

@Composable
fun CoinSection(
    quantity: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 30.dp),
        horizontalArrangement = Arrangement.End,
    ) {
        TextButton(
            onClick = {},
        ) {
            Text(
                text = "C",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 5.dp)
            )
            Text(
                text = quantity.toString(),
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Bold
            )
        }

        GlideImage(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .clip(CircleShape)
                .clickable(enabled = true, onClick = {}),
            imageModel = ImageBitmap.imageResource(R.drawable.question_mark),
            circularReveal = CircularReveal(duration = 250),
            error = ImageBitmap.imageResource(id = R.drawable.question_mark)
        )
    }
}

@Composable
fun ProfilePersonalInformationSection(
    profileImage: Painter,
    nickname: String,
    userRating: String,
    gender: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier
                .padding(start = 49.dp, end = 24.dp),

            ) {
            Image(
                painter = profileImage,
                contentDescription = "",
            )
        }

        Column() {
            Row()
            {
                NicknameColumn(textValue = nickname)
                Text(
                    text = stringResource(id = R.string.nim),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Light
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                UserRating(textValue = userRating, ratingColor = NewBadgeColor)

                Gender(textValue = gender, genderColor = MaleBadgeColor)
            }
        }
    }
}

@Composable
fun SellerRegistrationGameSection(
    isEditing: MutableState<Boolean>
){
    val sellerRegistrationGameList = remember {
        mutableStateListOf(
            "League of Legends - 플레티넘",
            "기타 게임 사전 협의",
        )
    }

    Column(
        modifier = Modifier
            .width(390.dp)
            .padding(
                start = 50.dp,
            )
    ) {
        TitleText(textValue = "판매자 등록 게임", bottomPaddingValue = 20)

        LazyColumn(
            modifier = Modifier
                .padding(bottom = 25.dp)
                .fillMaxWidth(),
        ) {
            item {
                ListRegistration(
                    bottomPaddingValue = 17,
                    isEditing = isEditing,
                    text = sellerRegistrationGameList[0],
                    textChange = { sellerRegistrationGameList[0] = it }
                )
            }

            item {
                ListRegistration(
                    bottomPaddingValue = 0,
                    isEditing = isEditing,
                    text = sellerRegistrationGameList[1],
                    textChange = { sellerRegistrationGameList[1] = it }
                )
            }
        }
    }
}

@Composable
fun RegisteredGameFeeSection(
    isEditing: MutableState<Boolean>
) {
    val registeredGameFeeList = remember {
        mutableStateListOf(
            "리그 오브 레전드 - 1시간 5000원 / 1판 3000원",
            "기타 게임 - 1시간 8000원",
        )
    }

    Column(
        modifier = Modifier
            .width(390.dp)
            .padding(
                start = 50.dp,
            )
    ) {
        TitleText(textValue = "등록 게임 비용", bottomPaddingValue = 20)

        LazyColumn(
            modifier = Modifier.padding(bottom = 25.dp)
        ) {
            item {
                ListRegistration(
                    bottomPaddingValue = 17,
                    isEditing = isEditing,
                    text = registeredGameFeeList[0],
                    textChange = { registeredGameFeeList[0] = it }
                )
            }

            item {
                ListRegistration(
                    bottomPaddingValue = 0,
                    isEditing = isEditing,
                    text = registeredGameFeeList[1],
                    textChange = { registeredGameFeeList[1] = it }
                )
            }
        }
    }
}

@Composable
fun SelfIntroductionSection(
    isEditing: MutableState<Boolean>
) {
    var (selfIntroduction, setSelfIntroduction) = remember {
        mutableStateOf(
            "안녕하세요~" +
                    "\n신규로 들어와서 같이 즐겁게 게임하실분 구하고 있습니다! 게임은 역시 즐겜!"
                    + "\n신규로 들어와서 같이 즐겁게 게임하실분 구하고 있습니다! 게임은 역시 즐겜!"
                    + "\n신규로 들어와서 같이 즐겁게 게임하실분 구하고 있습니다! 게임은 역시 즐겜!"
                    + "\n신규로 들어와서 같이 즐겁게 게임하실분 구하고 있습니다! 게임은 역시 즐겜!"
                    + "\n신규로 들어와서 같이 즐겁게 게임하실분 구하고 있습니다! 게임은 역시 즐겜!"
                    + "\n신규로 들어와서 같이 즐겁게 게임하실분 구하고 있습니다! 게임은 역시 즐겜!"
        )
    }
    
    Column(
        modifier = Modifier
            .padding(
                start = 50.dp,
            )
    ) {
        TitleText(textValue = "자기소개", bottomPaddingValue = 0)

        Spacer(modifier = Modifier.height(17.dp))

        SelfIntroductionInputField(
            isEditing = isEditing,
            selfIntroduction = selfIntroduction,
            setSelfIntroduction = setSelfIntroduction
        )
    }
}

@Composable
fun NicknameColumn(
    textValue: String
) {
    Text(
        text = textValue,
        color = Color.White,
        fontSize = 20.sp,
        fontFamily = notosanskr,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun UserRating(
    textValue: String,
    ratingColor: Color
) {
    Button(
        onClick = { },
        enabled = false,
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = ratingColor,
        ),
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .width(32.dp)
            .height(18.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = textValue,
            fontSize = 8.sp,
            color = Color.White,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun Gender(
    textValue: String,
    genderColor: Color
) {
    Button(
        onClick = { },
        enabled = false,
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = genderColor,
        ),
        modifier = Modifier
            .padding(start = 11.28.dp)
            .clip(shape = CircleShape)
            .size(24.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = textValue,
            color = Color.White,
            fontSize = 12.sp,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun TitleText(
    textValue: String,
    bottomPaddingValue: Int
) {
    Text(
        text = textValue,
        color = Color.White,
        fontSize = 20.sp,
        fontFamily = notosanskr,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = bottomPaddingValue.dp)
    )
}

@Composable
fun DrawDot(
    dotSize: Int,
    color: Color
) {
    Canvas(modifier = Modifier.size(dotSize.dp), onDraw = {
        val size = dotSize.dp.toPx()
        drawCircle(
            color = color,
            radius = size / 2f
        )
    })
}

@Composable
fun ListRegistration(
    bottomPaddingValue: Int,
    isEditing: MutableState<Boolean>,
    text: String,
    textChange: (String) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = bottomPaddingValue.dp)
    ) {
        DrawDot(dotSize = 5, color = PointColor)

        BasicTextField(
            enabled = isEditing.value,
            modifier = Modifier
                .width(275.dp)
                .height(22.dp)
                .padding(start = 21.dp)
                .focusRequester(focusRequester),
            value = text,
            onValueChange = textChange,
            textStyle = TextStyle(
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal,
                fontFamily = notosanskr,
            ),
            singleLine = true,
            cursorBrush = SolidColor(Color.White),
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = { focusRequester.requestFocus() },
                enabled = isEditing.value,
                modifier = Modifier
                    .then(Modifier.size(24.dp))
                    .alpha(if (!isEditing.value) 0f else 1f),

                ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_help_24),
                    contentDescription = "",
                )
            }
        }
    }
}

@Composable
fun SelfIntroductionInputField(
    isEditing: MutableState<Boolean>,
    selfIntroduction: String,
    setSelfIntroduction: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
    ) {
        TextField(
            readOnly = !isEditing.value,
            modifier = Modifier
                .width(331.dp)
                .height(115.dp)
                .clip(shape = RoundedCornerShape(15.dp)),
            textStyle = TextStyle(
                fontSize = 12.sp,
                color = Color.White,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Bold
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = SelfIntroduction
            ),
            value = selfIntroduction,
            onValueChange = setSelfIntroduction,
        )
    }
}

@Composable
fun ProfileEditButton(
    isEditing: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { isEditing.value = !isEditing.value },

            colors = ButtonDefaults.buttonColors(
                backgroundColor = ProfileEditingColor
            ),
            modifier = Modifier
                .clip(shape = RoundedCornerShape(30.dp))
                .width(101.dp)
                .height(40.dp),
            contentPadding = PaddingValues(0.dp),
        ) {
            Text(
                if (!isEditing.value) "편집" else "완료",
                fontSize = 15.sp,
                color = Color.White,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun Backward(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(start = 35.dp, top = 10.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                contentDescription = "",
            )
        }
    }
}