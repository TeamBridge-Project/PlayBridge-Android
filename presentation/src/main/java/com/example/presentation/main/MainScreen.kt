package com.example.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.presentation.main.component.FunctionSelectBar
import com.example.presentation.main.component.TopBar
import com.example.presentation.ui.common.LoadingIndicator
import com.example.presentation.ui.common.UiStatus
import com.example.presentation.ui.navigation.HomeScreens
import com.example.presentation.ui.theme.BackgroundColor
import org.orbitmvi.orbit.viewmodel.observe

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                is MainSideEffect.NavigateToRegistration -> {
                    navController.navigate(HomeScreens.SupportGameRegistrationScreen.route)
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(60.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ) {
        when (state.status) {
            UiStatus.Success -> {
                Spacer(modifier = Modifier.height(40.dp))
                state.profile?.let {
                    TopBar(
                        navController = navController,
                        it.nickname,
                        it.credit.toString()
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                FunctionSelectBar(
                    viewModel = viewModel
                )
            }
            else -> {
                LoadingIndicator()
            }

        }
    }

}

