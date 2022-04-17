package com.example.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("tokens")

@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext appContext: Context) {

    private val tokensDataStore = appContext.dataStore

    private val accessTokenKey = stringPreferencesKey("access_token")
    private val refreshTokenKey = stringPreferencesKey("refresh_token")

    suspend fun setAccessToken(accessToken: String) {
        tokensDataStore.edit { preferences ->
            preferences[accessTokenKey] = accessToken
        }
    }

    suspend fun setRefreshToken(refreshToken: String) {
        tokensDataStore.edit { preferences ->
            preferences[refreshTokenKey] = refreshToken
        }
    }

    val accessToken = tokensDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[accessTokenKey] ?: ""
        }
    val refreshToken = tokensDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[refreshTokenKey] ?: ""
        }
}