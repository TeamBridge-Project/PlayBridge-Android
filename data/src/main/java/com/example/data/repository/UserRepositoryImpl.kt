package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.di.IoDispatcher
import com.example.data.mapper.toData
import com.example.data.paging.UserPagingSource
import com.example.data.service.UserService
import com.example.domain.model.game.PlayingGameModel
import com.example.domain.model.user.LoginModel
import com.example.domain.model.user.ProfileUpdateModel
import com.example.domain.model.user.SignUpModel
import com.example.domain.model.user.UserModel
import com.example.domain.model.user.UserResponse
import com.example.domain.repository.UserRepository
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: UserService,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher
) : UserRepository {
    override suspend fun signUp(signUpModel: SignUpModel) =
        withContext(defaultDispatcher) {
            apiService.signUp(signUpModel.toData())
        }

    override suspend fun login(loginModel: LoginModel) =
        withContext(defaultDispatcher) {
            apiService.login(loginModel.toData())
        }

    override suspend fun getUser(query: Int): Flow<PagingData<UserModel>> =
        withContext(defaultDispatcher) {
            Pager(
                config = PagingConfig(
                    pageSize = 6
                ),
                pagingSourceFactory = { UserPagingSource(apiService, query) }
            ).flow
        }

    override suspend fun getProfile(uuid: String) =
        withContext(defaultDispatcher) {
            apiService.getProfile(uuid)
        }

    override suspend fun updateProfile(
        uuid: String,
        profileUpdateModel: ProfileUpdateModel
    ) =
        withContext(defaultDispatcher){
            apiService.updateProfile(uuid,profileUpdateModel)
        }

    override suspend fun addPlayingGame(
        uuid: String,
        playingGameModel: PlayingGameModel
    ) =
        withContext(defaultDispatcher){
            apiService.addPlayingGames(playingGameModel.toData(uuid))
        }
}
