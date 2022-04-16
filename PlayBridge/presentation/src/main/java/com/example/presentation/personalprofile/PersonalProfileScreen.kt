package com.example.presentation.personalprofile


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
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

@Preview(widthDp = 450, heightDp = 850)
@Composable
fun preview() {
    PersonalProfileScreen(navController = rememberNavController())
}

@Composable
fun PersonalProfileScreen(navController: NavController) {
    val isEditing = remember { mutableStateOf(false) }
    val sellerRegistrationGameList = remember {
        mutableStateListOf(
            "League of Legends - 플레티넘",
            "기타 게임 사전 협의",
        )
    }
    val registeredGameFeeList = remember {
        mutableStateListOf(
            "리그 오브 레전드 - 1시간 5000원 / 1판 3000원",
            "기타 게임 - 1시간 8000원",
        )
    }
    val (selfIntroduction, setSelfIntroduction) = remember {
        mutableStateOf(
            "안녕하세요~" +
                    "\n신규로 들어와서 같이 즐겁게 게임하실분 구하고 있습니다! 게임은 역시 즐겜!"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ) {
        Backward(navController = navController)
        Spacer(modifier = Modifier.height(30.47.dp))
        CoinSection(quantity = 3000)
        Spacer(modifier = Modifier.height(4.63.dp))
        ProfilePersonalInformationSection(
            profileImage = painterResource(id = R.drawable.ic_baseline_account_circle_24),
            nickname = stringResource(id = R.string.test_name),
            userRating = stringResource(id = R.string.new_rating),
            gender = stringResource(id = R.string.male),
            isEditing = isEditing
        )
        Spacer(modifier = Modifier.height(27.dp))
        SellerRegistrationGameSection(
            isEditing = isEditing,
            sellerRegistrationGameList = sellerRegistrationGameList
        )
        Spacer(modifier = Modifier.height(25.dp))
        RegisteredGameFeeSection(
            isEditing = isEditing,
            registeredGameFeeList = registeredGameFeeList
        )
        Spacer(modifier = Modifier.height(35.dp))
        SelfIntroductionSection(
            isEditing = isEditing,
            selfIntroduction = selfIntroduction,
            setSelfIntroduction = setSelfIntroduction
        )
        Spacer(modifier = Modifier.height(40.dp))
        ProfileEditButton(isEditing = isEditing)
    }
}

@Composable
fun CoinSection(
    quantity: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 44.5.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Top
    ) {
        TextButton(
            onClick = {},
            modifier = Modifier
                .height(30.2.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "C ",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = quantity.toString(),
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Composable
fun ProfilePersonalInformationSection(
    profileImage: Painter,
    nickname: String,
    userRating: String,
    gender: String,
    isEditing:  MutableState<Boolean>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.padding(start = 49.dp, end = 24.dp),
        ) {
            ProfileImage(isEditing = isEditing, profileImage = profileImage)
        }

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                NicknameColumn(textValue = nickname)
                Text(
                    text = stringResource(id = R.string.nim),
                    color = Color.White,
                    fontSize = 15.sp,
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(bottom = 1.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                UserRating(textValue = userRating, ratingColor = NewBadgeColor)
                Spacer(modifier = Modifier.width(11.28.dp))
                Gender(textValue = gender, genderColor = MaleBadgeColor)
            }
        }
    }
}

@Composable
fun ProfileImage(
    isEditing: MutableState<Boolean>,
    profileImage: Painter
) {
    IconButton(
        onClick = {},
        enabled = isEditing.value,
    ) {
        Image(
            painter = profileImage,
            contentDescription = "",
        )
    }
    Image(
        painter = painterResource(id = R.drawable.ic_baseline_add_circle_24),
        modifier = Modifier
            .alpha(if (!isEditing.value) 0f else 1f)
            .size(29.dp)
            .offset(70.dp, 70.dp),
        contentDescription = "",
    )
}

@Composable
fun SellerRegistrationGameSection(
    isEditing: MutableState<Boolean>,
    sellerRegistrationGameList: SnapshotStateList<String>
){
    Column(
        modifier = Modifier
            .width(331.dp)
            .padding(start = 50.dp)
    ) {
        Title(textValue = stringResource(id = R.string.seller_registration_game), isEditing = isEditing)
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            item {
                ListRegistration(
                    isEditing = isEditing,
                    text = sellerRegistrationGameList[0],
                    textChange = { sellerRegistrationGameList[0] = it }
                )
                Spacer(modifier = Modifier.height(34.dp))
            }

            item {
                ListRegistration(
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
    isEditing: MutableState<Boolean>,
    registeredGameFeeList: SnapshotStateList<String>,
) {
    Column(
        modifier = Modifier
            .width(331.dp)
            .padding(start = 50.dp)
    ) {
        Title(textValue = stringResource(id = R.string.registered_game_fee), isEditing = isEditing)
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            item {
                ListRegistration(
                    isEditing = isEditing,
                    text = registeredGameFeeList[0],
                    textChange = { registeredGameFeeList[0] = it }
                )
                Spacer(modifier = Modifier.height(34.dp))
            }

            item {
                ListRegistration(
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
    isEditing: MutableState<Boolean>,
    selfIntroduction: String,
    setSelfIntroduction: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp, end = 50.dp)
    ) {
        Title(textValue = stringResource(id = R.string.self_introduction), isEditing)
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
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(50))
            .background(color = ratingColor)
            .width(31.72.dp)
            .height(18.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = textValue,
            fontSize = 8.sp,
            color = Color.White,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
fun Gender(
    textValue: String,
    genderColor: Color
) {
    Box(
        modifier = Modifier
            .clip(shape = CircleShape)
            .size(24.dp)
            .background(color = genderColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = textValue,
            color = Color.White,
            fontSize = 12.sp,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
fun Title(
    textValue: String,
    isEditing: MutableState<Boolean>
) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = textValue,
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            painter = painterResource(id = R.drawable.test_image_sixteensix),
            modifier = Modifier
                .alpha(if(isEditing.value) 1f else 0f),
            contentDescription = "",
            alignment = Alignment.Center
        )
    }
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
    isEditing: MutableState<Boolean>,
    text: String,
    textChange: (String) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        DrawDot(dotSize = 5, color = PointColor)
        Spacer(modifier = Modifier.width(21.dp))
        BasicTextField(
            enabled = isEditing.value,
            modifier = Modifier
                .width(275.dp)
                .height(22.dp),
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
    }
}

@Composable
fun SelfIntroductionInputField(
    isEditing: MutableState<Boolean>,
    selfIntroduction: String,
    setSelfIntroduction: (String) -> Unit
) {
    TextField(
        readOnly = !isEditing.value,
        modifier = Modifier
            .fillMaxWidth()
            .height(115.dp)
            .clip(shape = RoundedCornerShape(15.dp)),
        textStyle = TextStyle(
            fontSize = 12.sp,
            color = Color.White,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Bold
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = SelfIntroduction,
            cursorColor = Color.White,
            trailingIconColor = Color.White
        ),
        value = selfIntroduction,
        onValueChange = setSelfIntroduction,
    )
}

@Composable
fun ProfileEditButton(
    isEditing: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
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
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = if(!isEditing.value) "편집" else "완료",
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
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(start = 30.dp, top = 10.dp)
                .height(24.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                contentDescription = "",
            )
        }
    }
}