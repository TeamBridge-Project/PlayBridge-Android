package com.example.presentation.ui.navigation

sealed class HomeScreens(val route: String) {
    object HomeScreen : HomeScreens("main_screen")
    object SupportGameRegistrationScreen : HomeScreens("support_game_registration_screen")
    object PersonalProfileScreen : HomeScreens("personal_profile_screen")
    object AboutProfileScreen : HomeScreens("about_profile_screen")
    object GameCostScreen : HomeScreens("game_cost_screen")
    object ReservationDetailsScreen : HomeScreens("reservation_details_screen")
}
