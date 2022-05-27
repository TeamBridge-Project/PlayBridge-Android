package com.example.data.mapper

import com.example.data.dto.requestbody.PlayingGamesRequestBody
import com.example.domain.model.game.PlayingGameModel

fun PlayingGameModel.toData(uuid: String) = PlayingGamesRequestBody(
    id = id,
    tier = tier,
    tierLevel = 0,
    feePerHour = feePerHour,
    feePerPlay = feePerHour,
    uuid = uuid
)