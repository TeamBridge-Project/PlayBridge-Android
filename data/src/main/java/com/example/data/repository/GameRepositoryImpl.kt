package com.example.data.repository

import com.example.data.di.IoDispatcher
import com.example.data.service.GameService
import com.example.domain.repository.GameRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gameService: GameService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : GameRepository {
    override suspend fun getGameList() =
        withContext(dispatcher) {
            gameService.getGameList()
        }
}