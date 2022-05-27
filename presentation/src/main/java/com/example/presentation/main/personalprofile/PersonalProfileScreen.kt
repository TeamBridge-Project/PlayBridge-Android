package com.example.presentation.main.personalprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.main.personalprofile.common.Backward
import com.example.presentation.main.personalprofile.common.ProfileEditButton
import com.example.presentation.main.personalprofile.components.CoinSection
import com.example.presentation.main.personalprofile.components.Description
import com.example.presentation.main.personalprofile.components.IntroSection
import com.example.presentation.main.personalprofile.components.UserInfoSection
import com.example.presentation.ui.common.LoadingIndicator
import com.example.presentation.ui.common.UiStatus
import com.example.presentation.ui.theme.BackgroundColor

@Composable
fun PersonalProfileScreen(
    navController: NavController,
    viewModel: PersonalProfileViewModel = hiltViewModel(),
) {
    val state by viewModel.container.stateFlow.collectAsState()
    val isEditing = remember { mutableStateOf(false) }
    val sellerRegistrationGameList = remember { mutableStateListOf("","") }
    val registeredGameFeeList = remember { mutableStateListOf("", "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ) {
        when(state.status){
            is UiStatus.Success -> {
                val user = checkNotNull(state.profile)
                Column(
                    modifier = Modifier
                        .padding(start = 42.dp, end = 42.dp, bottom = 5.dp, top = 24.dp)
                        .fillMaxWidth()
                ) {
                    Backward(navController = navController)

                    CoinSection(coin = user.credit.toString(), navController= navController)

                    Spacer(modifier = Modifier.height(4.dp))

                    UserInfoSection(
                        profileImage = painterResource(id = R.drawable.ic_baseline_account_circle_24),
                        nickname = user.nickname,
                        gender = user.gender,
                        isEditing = isEditing,
                        navController = navController
                    )

                    Spacer(modifier = Modifier.height(27.dp))

                    Description(
                        isEditing = isEditing,
                        sectionName = stringResource(id = R.string.seller_registration_game),
                        registrationList = sellerRegistrationGameList
                    )

                    Description(
                        isEditing = isEditing,
                        sectionName = stringResource(id = R.string.registration_game_costs),
                        registrationList = registeredGameFeeList
                    )

                    IntroSection(isEditing = isEditing)

                    Spacer(modifier = Modifier.height(40.dp))

                    ProfileEditButton(isEditing = isEditing)
                }
            }
            else ->{
                LoadingIndicator()
            }
        }

    }
}
