package com.example.presentation.main.personalprofile

import com.example.presentation.main.MainSideEffect

sealed class PersonalProfileSideEffect {
    object NavigateToMain : PersonalProfileSideEffect()
}