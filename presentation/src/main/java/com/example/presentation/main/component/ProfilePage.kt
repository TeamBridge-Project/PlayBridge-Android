@file:OptIn(ExperimentalPagerApi::class)

package com.example.presentation.main.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.domain.model.UserModel
import com.example.presentation.R
import com.example.presentation.main.MainViewModel
import com.example.presentation.ui.common.UiStatus
import com.example.presentation.ui.theme.ComponentInnerColor
import com.example.presentation.ui.theme.ConnectingDotColor
import com.example.presentation.ui.theme.MaleBadgeColor
import com.example.presentation.ui.theme.NewBadgeColor
import com.example.presentation.ui.theme.notosanskr
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage

@Composable
internal fun profilePage(viewModel: MainViewModel) {
    Spacer(Modifier.height(40.dp))
    ProfileArrangeBadges()
    ProfileBody(viewModel = viewModel)
}

@Composable
internal fun ProfileArrangeBadges() {
    val badgeList = listOf("게임", "성별", "비용", "등급")
    LazyRow(
        modifier = Modifier
            .padding(end = 22.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ) {
        items(badgeList) { badgeString ->
            Badge(badgeString)
        }
    }
}

@Composable
internal fun Badge(text: String) {
    Button(
        onClick = {},
        modifier = Modifier
            .width(60.dp)
            .height(32.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = ComponentInnerColor),
        shape = RoundedCornerShape(30.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = Color.White,
            )
        }
    }
    Spacer(modifier = Modifier.width(8.dp))
}


@Composable
internal fun ProfileBody(viewModel: MainViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    val pagerState = rememberPagerState()
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            viewModel.getUserList(page + 1)
        }
    }
    Column(Modifier.fillMaxSize()) {
        HorizontalPager(
            count = 6,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            when (state.status) {
                UiStatus.Success -> {
                    val items = state.userPagingData.collectAsLazyPagingItems()
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        userScrollEnabled = false
                    ) {
                        items(items.itemCount) { index ->
                            ProfileCard(items[index]!!)
                        }
                    }
                }
                else -> {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(32.dp)
                .padding(bottom = 16.dp),
            inactiveColor = ComponentInnerColor
        )
    }
}


@Composable
internal fun ProfileCard(user: UserModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(vertical = 10.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(30.dp),
        elevation = 3.dp,
        backgroundColor = ComponentInnerColor
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 8.dp
            ),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                DrawDot(dotSize = 5, color = ConnectingDotColor)
                Spacer(modifier = Modifier.width(5.dp))
                ProFileText(
                    text = "현재 접속 중",
                    fontSize = 8,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            CoilImage(
                modifier = Modifier
                    .width(130.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(20.dp)),
                imageModel = ImageBitmap.imageResource(R.drawable.nyong),
                circularReveal = CircularReveal(duration = 250),
                error = ImageBitmap.imageResource(id = R.drawable.nyong)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProFileText(
                    text = user.nickname + "님",
                    fontSize = 12,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.width(4.dp))
                UserRating(textValue = "신규")
                Spacer(modifier = Modifier.width(3.dp))
                Gender(
                    textValue = when (user.gender) {
                        "m" -> "남"
                        else -> "여"
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DrawDot(dotSize = 3, color = Color.Green)
                    Spacer(modifier = Modifier.width(5.dp))
                    ProFileText(
                        text = "오버워치 - 실버",
                        fontSize = 8,
                        fontWeight = FontWeight.Light
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DrawDot(dotSize = 3, color = Color.Cyan)
                    Spacer(modifier = Modifier.width(5.dp))
                    ProFileText(
                        text = "리그 오브 레전드 - 실버",
                        fontSize = 8,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    }
}

@Composable
internal fun DrawDot(dotSize: Int, color: Color) {
    Canvas(modifier = Modifier.size(dotSize.dp), onDraw = {
        val size = dotSize.dp.toPx()
        drawCircle(
            color = color,
            radius = size / 2f
        )
    })
}

@Composable
internal fun ProFileText(
    text: String,
    fontSize: Int,
    fontWeight: FontWeight
) {
    Text(
        text = text,
        fontFamily = notosanskr,
        fontWeight = fontWeight,
        fontSize = fontSize.sp,
        color = Color.White
    )
}

@Composable
internal fun Gender(textValue: String) {
    Button(
        onClick = { },
        enabled = false,
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = MaleBadgeColor,
        ),
        modifier = Modifier
            .clip(shape = CircleShape)
            .size(16.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = textValue,
            color = Color.White,
            fontSize = 8.sp,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
internal fun UserRating(
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
