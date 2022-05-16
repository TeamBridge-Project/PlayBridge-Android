package com.example.presentation.main

import androidx.paging.PagingData
import com.example.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

sealed class MainState{
    object Empty : MainState()
    object Loading : MainState()
    data class ItemLoaded(val users : Flow<PagingData<UserModel>>) : MainState()
}

