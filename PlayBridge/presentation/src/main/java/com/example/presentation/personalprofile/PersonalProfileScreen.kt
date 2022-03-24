package com.example.presentation.personalprofile

import android.graphics.Color.alpha
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun preview(){
    PersonalProfileScreen(navController = rememberNavController())
}

@Composable
fun PersonalProfileScreen(navController: NavController) {
    val isEditing = remember { mutableStateOf(false) }
    var (text,setText) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        IconButton(
            onClick = {navController.popBackStack()},
            modifier = Modifier.padding(start = 25.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                contentDescription = "",
            )
        }

        CoinQuestionColumn(
            coinSymbol = stringResource(id = R.string.coin_symbol),
            textValue = stringResource(id = R.string.coin_quantity),
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(start = 49.dp, end = 24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_account_circle_24),
                    contentDescription = "",
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row()
                {
                    NicknameColumn(
                        textValue = stringResource(id = R.string.dungledungle),
                        fontWeight = FontWeight.Bold
                    )
                    NicknameColumn(
                        textValue = stringResource(id = R.string.nim),
                        fontWeight = FontWeight.Light
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    UserRating(textValue = stringResource(id = R.string.new_rating))

                    Gender(textValue = stringResource(id = R.string.male))
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 27.dp,
                    start = 50.dp,
                )
        ) {
            TitleText(textValue = "판매자 등록 게임", bottomPaddingValue = 20)

            LazyColumn(
                modifier = Modifier.padding(bottom = 25.dp),
            ) {
                item {
                    SellerRegistrationGameItem(
                        bottomPaddingValue = 17,
                        isEditing = isEditing,
                        text = text,
                        textChange = setText
                    )
                }

                item {
                    SellerRegistrationGameItem(
                        bottomPaddingValue = 0,
                        isEditing = isEditing,
                        text = text,
                        textChange = setText
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 50.dp,
                )
        ) {
            TitleText(textValue = "등록 게임 비용", bottomPaddingValue = 20)

            LazyColumn(
                modifier = Modifier.padding(bottom = 25.dp)
            ) {
                item {
                    SellerRegistrationGameItem(
                        bottomPaddingValue = 17,
                        isEditing = isEditing,
                        text = text,
                        textChange = setText
                    )
                }

                item {
                    SellerRegistrationGameItem(
                        bottomPaddingValue = 0,
                        isEditing = isEditing,
                        text = text,
                        textChange = setText
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 50.dp,
                )
        ) {
            TitleText(textValue = "자기소개", bottomPaddingValue = 0)

            Row(
                modifier = Modifier.padding(top = 17.dp),
            ) {
                Button(
                    onClick = { },
                    enabled = false,
                    colors = ButtonDefaults.buttonColors(
                        disabledBackgroundColor = SelfIntroduction,
                    ),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(15.dp))
                        .width(331.dp)
                        .height(115.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "안녕하세요~\n" +
                                "\n" +
                                "신규로 들어와서 같이 즐겁게 게임하실분 구하고\n" +
                                "있습니다! 게임은 역시 즐겜!",
                        fontSize = 12.sp,
                        color = Color.White,
                        fontFamily = notosanskr,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        ProfileEditButton(isEditing = isEditing)
    }
}

@Composable
fun CoinQuestionColumn(
    coinSymbol: String,
    textValue: String
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
                text = coinSymbol,
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 5.dp)
            )
            Text(
                text = textValue,
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Bold
            )
        }

        /*GlideImage(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .clip(CircleShape)
                .clickable(enabled = true, onClick = {}),
            imageModel = ImageBitmap.imageResource(R.drawable.question_mark),
            circularReveal = CircularReveal(duration = 250),
            error = ImageBitmap.imageResource(id = R.drawable.question_mark)
        )*/
    }
}

@Composable
fun NicknameColumn(
    textValue: String,
    fontWeight: FontWeight?
) {
    Text(
        text = textValue,
        color = Color.White,
        fontSize = 20.sp,
        fontFamily = notosanskr,
        fontWeight = fontWeight
    )
}

@Composable
fun UserRating(
    textValue: String
) {
    Button(
        onClick = { },
        enabled = false,
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = NewBadgeColor,
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
fun Gender(textValue: String) {
    Button(
        onClick = { },
        enabled = false,
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = MaleBadgeColor,
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
fun SellerRegistrationGameItem(
    bottomPaddingValue: Int,
    isEditing: MutableState<Boolean>,
    text:String,
    textChange: (String) -> Unit,

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = bottomPaddingValue.dp)
    ) {
        DrawDot(dotSize = 5, color = PointColor)

        OutlinedTextField(
            enabled = isEditing.value,
            modifier = Modifier
                .width(216.dp)
                .height(40.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Black
            ),
            value = text,
            onValueChange = textChange,
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal,
                fontFamily = notosanskr,
            ),
            maxLines = 1,
            singleLine = true,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = { },
                enabled = isEditing.value,
                modifier = Modifier
                    .padding(end = 30.dp, bottom = 0.dp)
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
            onClick = { isEditing.value = !isEditing.value  },

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
                if(!isEditing.value) "편집" else "완료",
                fontSize = 15.sp,
                color = Color.White,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Bold
            )
        }
    }
}