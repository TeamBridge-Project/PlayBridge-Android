package com.example.presentation.main.personalprofile

import com.example.domain.model.user.UserModel
import com.example.presentation.ui.common.UiStatus

data class PersonalProfileState(
    val status: UiStatus = UiStatus.Loading,
    val profile: UserModel? = null
)