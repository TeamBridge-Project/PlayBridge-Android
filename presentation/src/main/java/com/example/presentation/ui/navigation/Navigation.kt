package com.example.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.main.registration.aboutprofile.AboutProfileScreen
import com.example.presentation.main.MainScreen
import com.example.presentation.main.personalprofile.PersonalProfileScreen
import com.example.presentation.main.registration.gamecost.GameCostScreen
import com.example.presentation.main.registration.supportgame.SupportGameScreen
import com.example.presentation.main.reservation.ReservationDetailsScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeScreens.HomeScreen.route
    ) {
        composable(route = HomeScreens.HomeScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = HomeScreens.SupportGameRegistrationScreen.route) {
            SupportGameScreen(navController = navController)
        }
        composable(route = HomeScreens.PersonalProfileScreen.route) {
            PersonalProfileScreen(navController = navController)
        }
        composable(route = HomeScreens.AboutProfileScreen.route) {
            AboutProfileScreen(navController = navController)
        }
        composable(route = HomeScreens.GameCostScreen.route) {
            GameCostScreen(navController = navController)
        }
        composable(route = HomeScreens.ReservationDetailsScreen.route) {
            ReservationDetailsScreen(navController = navController)
        }
    }
}
