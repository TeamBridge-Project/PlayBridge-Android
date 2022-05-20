@file:OptIn(ExperimentalPagerApi::class)

package com.example.presentation.main.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.main.MainViewModel
import com.example.presentation.ui.navigation.HomeScreens
import com.example.presentation.ui.theme.SellerRegistrationColor
import com.example.presentation.ui.theme.notosanskr
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.launch

@Composable
internal fun TopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .padding(start = 40.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CoilImage(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clip(CircleShape)
                .clickable { navController.navigate(HomeScreens.PersonalProfileScreen.route) },
            imageModel = ImageBitmap.imageResource(R.drawable.profile_image),
            contentScale = ContentScale.Crop,
            circularReveal = CircularReveal(duration = 250),
            placeHolder = ImageBitmap.imageResource(R.drawable.profile_image),
            error = ImageBitmap.imageResource(R.drawable.profile_image)
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
            CoilImage(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clip(CircleShape)
                    .clickable(enabled = true, onClick = {}),
                imageModel = ImageBitmap.imageResource(R.drawable.question_mark),
                contentScale = ContentScale.Crop,
                circularReveal = CircularReveal(duration = 250),
                placeHolder = ImageBitmap.imageResource(R.drawable.question_mark),
                error = ImageBitmap.imageResource(R.drawable.question_mark)
            )
        }
    }
}

@Composable
internal fun FunctionSelectBar(
    navController: NavController,
    viewModel: MainViewModel
) {
    val tabData = listOf(
        "프로필", "채팅"
    )
    val pagerState = rememberPagerState(
        initialPage = 0
    )
    val tabIndex = pagerState.currentPage
    val scope = rememberCoroutineScope()
    Column {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            TabRow(
                selectedTabIndex = tabIndex,
                modifier = Modifier
                    .width(250.dp)
                    .padding(start = 40.dp),
            ) {
                tabData.forEachIndexed { index, text ->
                    Tab(
                        selected = tabIndex == index,
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
            Spacer(Modifier.width(60.dp))
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        navController.navigate(HomeScreens.SupportGameRegistrationScreen.route)
                    }
                },
                modifier = Modifier
                    .width(80.dp)
                    .height(30.dp),
                elevation = FloatingActionButtonDefaults.elevation(8.dp),
                backgroundColor = SellerRegistrationColor
            ) {
                ProFileText(stringResource(R.string.seller_registration), 8, FontWeight.Bold)
            }
        }

        HorizontalPager(
            count = 2,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { index ->
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                when (tabData[index]) {
                    tabData[0] -> profilePage(viewModel = viewModel)
                    tabData[1] -> Text("언제만드냐 이건")
                }
            }
        }
    }
}
