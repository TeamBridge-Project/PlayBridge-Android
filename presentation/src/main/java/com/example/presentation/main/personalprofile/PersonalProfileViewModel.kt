package com.example.presentation.main.personalprofile

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetProfileUseCase
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
class PersonalProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val dataStore: DataStoreManager,
) : ViewModel(), ContainerHost<PersonalProfileState,PersonalProfileSideEffect> {

    override val container = container<PersonalProfileState, PersonalProfileSideEffect>(PersonalProfileState())

    init{
        getUserProfile()
    }

    private fun getUserProfile() = intent {
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