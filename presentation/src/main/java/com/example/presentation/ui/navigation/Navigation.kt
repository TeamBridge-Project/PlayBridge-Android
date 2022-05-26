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
import com.example.presentation.ui.navigation.HomeScreens.GameCostScreen.getDestination

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
            SupportGameScreen(
                onBackPressed = { navController.popBackStack() },
                navController = navController)
        }
        composable(route = HomeScreens.PersonalProfileScreen.route) {
            PersonalProfileScreen(navController = navController)
        }
        composable(
            route = HomeScreens.AboutProfileScreen.route,
            arguments = HomeScreens.AboutProfileScreen.argumentList
        ) { backStackEntry ->
            val (game, tier, gameCost) = HomeScreens.AboutProfileScreen.parseArguments(backStackEntry)
            AboutProfileScreen(navController = navController, game = game, tier = tier, gameCost = gameCost)
        }
        composable(
            route = HomeScreens.GameCostScreen.route,
            arguments = HomeScreens.GameCostScreen.argumentList
        ) { backStackEntry ->
            val (game, tier) = HomeScreens.GameCostScreen.parseArguments(backStackEntry)
            GameCostScreen(navController = navController, game = game, tier = tier)
        }
        composable(route = HomeScreens.ReservationDetailsScreen.route) {
            ReservationDetailsScreen(navController = navController)
        }
    }
}