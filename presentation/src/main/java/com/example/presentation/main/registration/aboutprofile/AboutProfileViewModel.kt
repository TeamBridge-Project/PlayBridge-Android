package com.example.presentation.main.registration.aboutprofile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.game.PlayingGameModel
import com.example.domain.model.user.ProfileUpdateModel
import com.example.domain.usecase.AddPlayingGameUseCase
import com.example.domain.usecase.UpdateProfileUseCase
import com.example.local.datastore.DataStoreManager
import com.example.presentation.util.gameHashMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class AboutProfileViewModel @Inject constructor(
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val addPlayingGameUseCase: AddPlayingGameUseCase,
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {

    fun updateProfile(profileUri: Uri?, aboutMe: String) = viewModelScope.launch {
        val uuid = runBlocking(Dispatchers.IO) {
            dataStoreManager.uuid.first()
        }
        updateProfileUseCase(uuid, ProfileUpdateModel(profileUri.toString(), aboutMe))
    }

    fun addPlayingGame(game: String, tier: String, feePerHour: Int) = viewModelScope.launch {
        val uuid = runBlocking(Dispatchers.IO) {
            dataStoreManager.uuid.first()
        }
        addPlayingGameUseCase(uuid, PlayingGameModel(gameHashMap[game]!!, tier, feePerHour))
    }
}