package com.example.presentation.main

import androidx.paging.PagingData
import com.example.domain.model.UserModel
import com.example.presentation.ui.common.UiStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MainState(
    val status: UiStatus? = null,
    val userPagingData: Flow<PagingData<UserModel>> = emptyFlow(),
    val profile: UserModel? = null
)
