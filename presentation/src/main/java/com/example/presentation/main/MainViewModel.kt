package com.example.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.base.processMore
import com.example.domain.model.UserModel
import com.example.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel(), ContainerHost<MainState,Nothing> {

    override val container =  container<MainState, Nothing>(MainState.Empty)
    fun getUserList(page: Int) = intent{
        reduce {
            MainState.Loading
        }
        getUserUseCase(page).onSuccess {
            reduce{
                MainState.ItemLoaded(it.cachedIn(viewModelScope))
            }
        }
    }

}