package com.example.presentation.personalprofilepage

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.ui.theme.*

@Preview
@Composable
fun PersonalProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier.padding(top = 35.dp, start = 15.dp)
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
                modifier = Modifier.padding(bottom = 25.dp)
            ) {
                item {
                    SellerRegistrationGameItem(
                        bottomPaddingValue = 34,
                        textValue = "League of Legends - 플레티넘"
                    )
                }

                item {
                    SellerRegistrationGameItem(
                        bottomPaddingValue = 0,
                        textValue = "기타 게임 사전 협의"
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
                        bottomPaddingValue = 34,
                        textValue = "League of Legends - 1시간 5000원 / 1판 3000원"
                    )
                }

                item {
                    SellerRegistrationGameItem(
                        bottomPaddingValue = 0,
                        textValue = "기타 게임 - 1시간 8000원",
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {},

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
                    text = "편집",
                    fontSize = 15.sp,
                    color = Color.White,
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CoinQuestionColumn(
    coinSymbol: String,
    textValue: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
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

        IconButton(
            onClick = {},
            modifier = Modifier
                .padding(end = 30.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_help_24),
                contentDescription = "",
            )
        }
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
    textValue: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = bottomPaddingValue.dp)
    ) {
        DrawDot(dotSize = 5, color = PointColor)

        Text(
            text = textValue,
            color = Color.White,
            fontSize = 12.sp,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 21.dp)
        )
    }
}
