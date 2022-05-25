package com.example.presentation.main.personalprofile.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.main.common.GenderBadge
import com.example.presentation.ui.common.noRippleClickable
import com.example.presentation.ui.navigation.HomeScreens
import com.example.presentation.ui.theme.notosanskr
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun UserInfoSection(
    profileImage: Painter,
    nickname: String,
    gender: String,
    isEditing: MutableState<Boolean>,
    navController: NavController
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box {
            CoilImage(
                modifier = Modifier
                    .size(104.dp)
                    .clip(CircleShape)
                    .noRippleClickable {
                        if (isEditing.value) {
                            navController.navigate(HomeScreens.HomeScreen.route)
                        } else {
                            false
                        }
                    },
                imageModel = profileImage,
                circularReveal = CircularReveal(duration = 250),
                error = painterResource(id = R.drawable.ic_baseline_account_circle_24),
            )

            CoilImage(
                modifier = Modifier
                    .offset(68.dp, 68.dp)
                    .size(29.dp)
                    .clip(CircleShape)
                    .alpha(if (!isEditing.value) 0f else 1f),
                imageModel = painterResource(id = R.drawable.plus_icon),
                circularReveal = CircularReveal(duration = 250),
                error = painterResource(id = R.drawable.plus_icon),
            )
        }

        Spacer(modifier = Modifier.width(24.dp))

        Column {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)) {
                        append(nickname)
                    }
                    append(stringResource(id = R.string.nim))
                },
                color = Color.White,
                fontSize = 15.sp,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Light
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row { GenderBadge(gender = gender, badgeSize = 24.dp, textSize = 12.sp) }
        }
    }
}