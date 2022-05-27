package com.example.presentation.main.registration.supportgame

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetGameUseCase
import com.example.local.datastore.DataStoreManager
import com.example.presentation.ui.common.UiStatus
import com.example.presentation.util.gameHashMap
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SupportGameViewModel @Inject constructor(
    private val dataStore: DataStoreManager,
    private val getGameUseCase: GetGameUseCase
) : ViewModel(), ContainerHost<SupportGameState, SupportGameSideEffect> {

    override val container = container<SupportGameState, SupportGameSideEffect>(SupportGameState())

    init {
        getGameList()
    }

    private fun getGameList() = intent {
        getGameUseCase().suspendOnSuccess {
            data.result.forEach{
                gameHashMap[it.name] = it.name
            }
            reduce {
                state.copy(
                    status = UiStatus.Success,
                    gameList = data.result.toList()
                )
            }
        }
    }

    fun onRegister(game: String, tier: String) = intent{
        postSideEffect(SupportGameSideEffect.NavigateToGameCostScreen(game,tier))
        reduce { state.copy(status = UiStatus.Success) }
    }
}