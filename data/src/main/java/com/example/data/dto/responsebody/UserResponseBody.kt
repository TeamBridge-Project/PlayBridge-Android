package com.example.data.dto.responsebody

import java.util.Date

data class UserResponseBody(
    val status: Boolean,
    val result: List<User>,
)

data class User(
    val email: String,
    val password: String,
    val nickname: String,
    val profileIcon: String,
    val gender: String,
    val birthday: Date,
    val agreeEmail: Boolean,
    val credit: Int,
    val playingGames: List<PlayingGame>,
    val gameFee: List<GameFee>,
    val aboutMe: String,
    val registeredDate: Date
)

data class PlayingGame(
    val id: String,
    val tier: String,
    val feePerPlay: Int
)

data class GameFee(
    val id: String,
    val feePerHour: Int,
    val feePerPlay: Int
)