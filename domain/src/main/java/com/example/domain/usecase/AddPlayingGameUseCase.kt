package com.example.domain.usecase

import com.example.domain.model.game.PlayingGameModel
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class AddPlayingGameUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        uuid: String,
        playingGameModel: PlayingGameModel
    ) = repository.addPlayingGame(uuid, playingGameModel)
}