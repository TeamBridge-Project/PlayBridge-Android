package com.example.domain.usecase

import com.example.domain.repository.GameRepository
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke() =
        repository.getGameList()

}