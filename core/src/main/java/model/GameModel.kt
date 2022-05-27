package model

import utils.GameType


data class GameModel(
    val id: Int,
    val title: String,
    val year: Int,
    val description: String,
    val imageUrl: String,
    val thumbnail: String,
    val type: GameType,
    val rankingLatest: String
)
