package com.example.presentation.main.reservation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.main.reservation.component.ReservationContent
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.ReservationAcceptButtonColor
import com.example.presentation.ui.theme.ReservationRefuseButtonColor
import com.example.presentation.ui.theme.SelfIntroduction
import com.example.presentation.ui.theme.notosanskr

@Composable
internal fun ReservationDetailsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 35.dp, top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                    contentDescription = "",
                )
            }

            Text(
                text = stringResource(id = R.string.reservation_details),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontFamily = notosanskr
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Column(
            modifier = Modifier.padding(start = 70.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.nyong),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(99.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(19.dp))

                Text(
                    text = buildAnnotatedString {
                        append("둥글둥글")
                        withStyle(SpanStyle(fontSize = 15.sp, fontWeight = FontWeight.Medium)) {
                            append("님")
                        }
                    },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontFamily = notosanskr
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            ReservationContent(
                textValue = stringResource(id = R.string.reservation_date),
                contentText = "2022.4.9 토요일"
            )

            Spacer(modifier = Modifier.height(29.dp))

            ReservationContent(
                textValue = stringResource(id = R.string.hours_of_use),
                contentText = "14 : 00 - 17 : 00"
            )

            Spacer(modifier = Modifier.height(29.dp))

            ReservationContent(
                textValue = stringResource(id = R.string.reservation_fee),
                contentText = "300"
            )

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = stringResource(id = R.string.memo),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontFamily = notosanskr
            )

            Spacer(modifier = Modifier.height(17.dp))

            Row(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
            ) {
                TextField(
                    readOnly = true,
                    modifier = Modifier
                        .width(300.dp)
                        .height(170.dp)
                        .clip(shape = RoundedCornerShape(30.dp)),
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        color = Color.White,
                        fontFamily = notosanskr,
                        fontWeight = FontWeight.Bold
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = SelfIntroduction
                    ),
                    value = "안녕하세요 ~ \n" +
                            "\n" +
                            "들어오시기 전에 채팅으로 게임 닉네임만\n" +
                            "알려주세요!",
                    onValueChange = { } ,
                )
            }

            Spacer(modifier = Modifier.height(60.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ReservationAcceptButtonColor
                ),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(30.dp))
                    .width(101.dp)
                    .height(40.dp),
                contentPadding = PaddingValues(0.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.reservation_accept),
                    fontSize = 12.sp,
                    color = Color.White,
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(23.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ReservationRefuseButtonColor
                ),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(30.dp))
                    .width(101.dp)
                    .height(40.dp),
                contentPadding = PaddingValues(0.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.reservation_refuse),
                    fontSize = 12.sp,
                    color = Color.White,
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

