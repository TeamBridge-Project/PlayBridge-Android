package com.example.presentation.main.message

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.presentation.R
import com.example.presentation.main.message.components.MessageCard

@Composable
internal fun MessageBoxScreen() {
    val userProfileImage = listOf(
        painterResource(id = R.drawable.nyong),
        painterResource(id = R.drawable.nyong)
    )
    val gender = listOf("m", "f")

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(
            listOf("둥글둥글", "멍냥몬"),
        ) {
                index, item ->
                    MessageCard(
                        profileImage = userProfileImage[index],
                        nickName = item,
                        gender = gender[index]
                    )
        }
    }
}
