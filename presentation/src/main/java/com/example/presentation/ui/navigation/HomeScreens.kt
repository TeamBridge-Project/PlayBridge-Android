package com.example.presentation.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.presentation.ui.navigation.annotation.ComposeDestination

sealed class HomeScreens(val route: String) {
    object HomeScreen : HomeScreens("main_screen")
    object PersonalProfileScreen : HomeScreens("personal_profile_screen")
    object ReservationDetailsScreen : HomeScreens("reservation_details_screen")
    object SupportGameRegistrationScreen : HomeScreens("support_game_registration_screen")
    object AboutProfileScreen : HomeScreens("about_profile_screen?game={game}&tier={tier}&gameCost={gameCost}"){
        data class AboutProfileScreenArgs(
            val game: String,
            val tier: String,
            val gameCost: Int,
        )
        fun parseArguments(backStackEntry: NavBackStackEntry): AboutProfileScreenArgs {
            return AboutProfileScreenArgs(
                game = backStackEntry.arguments?.getString("game") ?: "",
                tier = backStackEntry.arguments?.getString("tier") ?: "",
                gameCost = backStackEntry.arguments?.getInt("gameCost") ?: 0,
            )
        }

        val argumentList: MutableList<NamedNavArgument>
            get() = mutableListOf(
                navArgument("game") {
                    type = NavType.StringType
                },
                navArgument("tier") {
                    type = NavType.StringType
                },
                navArgument("gameCost") {
                    type = NavType.IntType
                }
            )

        fun getDestination(game: String, tier: String, gameCost: Int): String {
            return "about_profile_screen?game=$game&tier=$tier&gameCost=$gameCost"
        }
    }

    object GameCostScreen : HomeScreens("game_cost_screen?game={game}&tier={tier}") {
        data class GameCostScreenArgs(
            val game: String,
            val tier: String,
        )
        fun parseArguments(backStackEntry: NavBackStackEntry): GameCostScreenArgs {
            return GameCostScreenArgs(
                game = backStackEntry.arguments?.getString("game") ?: "",
                tier = backStackEntry.arguments?.getString("tier") ?: "",
            )
        }

        val argumentList: MutableList<NamedNavArgument>
            get() = mutableListOf(
                navArgument("game") {
                    type = NavType.StringType
                },
                navArgument("tier") {
                    type = NavType.StringType
                },
            )

        fun getDestination(game: String, tier: String): String {
            return "game_cost_screen?game=$game&tier=$tier"
        }

    }
}

@ComposeDestination
abstract class GameCostPage {
    abstract val game: String
    abstract val tier: String
}
