package com.example.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.aboutprofile.AboutProfileScreen
import com.example.presentation.main.MainScreen
import com.example.presentation.personalprofile.PersonalProfileScreen
import com.example.presentation.registration.GameCostScreen
import com.example.presentation.registration.SupportGameScreen

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
    }
}
