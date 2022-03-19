package com.example.presentation.main

import android.os.Bundle
import android.provider.ContactsContract

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.material.*

import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign


import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = BackgroundColor
                )
            }

            PlayBridgeTheme() {
                MainScreen()
            }

        }
    }
}

@Composable
fun MainScreen() {
    Spacer(modifier = Modifier.height(60.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        TopBar()
        Spacer(modifier = Modifier.height(30.dp))
        FunctionSelectBar()
    }

}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.padding(start = 40.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GlideImage(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clip(CircleShape)
                .clickable(enabled = true, onClick = {}),
            imageModel = ImageBitmap.imageResource(R.drawable.profile_image),
            circularReveal = CircularReveal(duration = 250),
            error = ImageBitmap.imageResource(id = R.drawable.profile_image)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "OOO님", color = Color.White)
        }
        Row(
            Modifier.weight(1f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "C  3000", color = Color.White, textAlign = TextAlign.Right)
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
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FunctionSelectBar() {
    val tabData = listOf(
        "프로필", "채팅"
    )
    val pagerState = rememberPagerState(
        initialPage = 0
    )
    val tabIndex = pagerState.currentPage
    val scope = rememberCoroutineScope()
    Column {
        TabRow(
            selectedTabIndex = tabIndex,
            modifier = Modifier
                .width(250.dp)
                .padding(start = 40.dp),
        ) {
            tabData.forEachIndexed { index, text ->
                Tab(selected = tabIndex == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                page = index,
                                initialVelocity = 10f
                            )
                        }
                    },
                    text = {
                        Text(
                            text = text, fontFamily = notosanskr,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            }
        }
        HorizontalPager(
            count = 10,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { index ->
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                when (tabData[index]) {
                    tabData[0] -> profilePage()
                    tabData[1] -> Text("미구현")
                }
            }
        }
    }


}

@Composable
fun profilePage() {
    Spacer(Modifier.height(40.dp))
    ProfileArrangeBadges()
    ProfileBody()

}

@Composable
fun ProfileArrangeBadges() {
    Row(
        modifier = Modifier
            .padding(end = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ) {
        Badge("게임")
        Spacer(modifier = Modifier.width(8.dp))
        Badge("성별")
        Spacer(modifier = Modifier.width(8.dp))
        Badge("비용")
        Spacer(modifier = Modifier.width(8.dp))
        Badge("등급")
    }
}

@Composable
fun Badge(text: String) {
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
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProfileBody() {
    Column(Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState()
        HorizontalPager(
            count = 6,
            state = pagerState,
            // Add 32.dp horizontal padding to 'center' the pages
            contentPadding = PaddingValues(horizontal = 32.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) { page ->
            ProfileCardLayout()
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
                .padding(bottom = 16.dp),
            inactiveColor = ComponentInnerColor
        )
    }
}

@Composable
fun ProfileCardLayout() {
    val numbers = (0..3).toList()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(numbers.size) {
            ProfileCard()
        }
    }
}

@Composable
fun ProfileCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(vertical = 10.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(30.dp),
        elevation = 3.dp,
        backgroundColor = ComponentInnerColor
    ) {
        Column() {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                drawDot(dotSize = 5, color = ConnectingDotColor)
                Spacer(modifier = Modifier.width(5.dp))
                ProFileText(
                    text = "현재 접속 중",
                    fontSize = 8,
                    fontWeight = FontWeight.Medium
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .padding(vertical = 7.dp, horizontal = 13.dp)
            ) {
                GlideImage(
                    modifier = Modifier
                        .height(100.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    imageModel = ImageBitmap.imageResource(R.drawable.nyong),
                    circularReveal = CircularReveal(duration = 250),
                    error = ImageBitmap.imageResource(id = R.drawable.nyong)
                )
            }

        }
    }
}

@Composable
fun drawDot(dotSize: Int, color: Color) {
    Canvas(modifier = Modifier.size(dotSize.dp), onDraw = {
        val size = dotSize.dp.toPx()
        drawCircle(
            color = color,
            radius = size / 2f
        )

    })
}

@Composable
fun ProFileText(
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