package com.example.presentation.ui.navigation

sealed class Screens(val route: String){
    object HomeScreen : Screens("main_screen")
    object SupportGameRegistrationScreen : Screens("support_game_registration_screen")
    object PersonalProfileScreen : Screens("personal_profile_screen")
    object AboutProfileScreen : Screens("about_profile_screen")
    object GameCostScreen : Screens("game_cost_screen")
}
