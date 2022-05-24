package com.example.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.domain.usecase.GetProfileUseCase
import com.example.domain.usecase.GetUserUseCase
import com.example.local.datastore.DataStoreManager
import com.example.presentation.ui.common.UiStatus
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val dataStore: DataStoreManager
) : ViewModel(), ContainerHost<MainState, Nothing> {

    override val container = container<MainState, Nothing>(MainState())

    init{
        getUserProfile()
    }

    fun getUserList(page: Int) = intent {
        reduce { state.copy(status = UiStatus.Loading) }
        getUserUseCase(page).onSuccess {
            reduce {
                state.copy(
                    status = UiStatus.Success,
                    userPagingData = it.cachedIn(viewModelScope)
                )
            }
        }
    }

    fun getUserProfile() = intent {
        reduce { state.copy(status = UiStatus.Loading) }
        val uuid = runBlocking {
            dataStore.uuid.first()
        }
        getProfileUseCase(uuid).suspendOnSuccess {
            reduce {
                state.copy(
                    status = UiStatus.Success,
                    profile = data.result[0]
                )
            }
        }
    }
}