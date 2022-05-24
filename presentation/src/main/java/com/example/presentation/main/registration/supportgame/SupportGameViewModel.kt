package com.example.presentation.main.registration.supportgame

import androidx.lifecycle.ViewModel
import com.example.local.datastore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SupportGameViewModel @Inject constructor(
    private val dataStore: DataStoreManager
) : ViewModel(){

}