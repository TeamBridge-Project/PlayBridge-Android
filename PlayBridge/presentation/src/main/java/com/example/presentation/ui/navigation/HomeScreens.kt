package com.example.presentation.ui.navigation

sealed class Screens(val route: String){
    object HomeScreen : Screens("main_screen")
    object SupportGameRegistrationScreen : Screens("registration_screen_support_game")
}
