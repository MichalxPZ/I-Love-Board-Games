package pl.org.akai.ranking_history_domain.model

import utils.GameType

data class GameRankingModel(

    val id: Int = 0,
    val title: String = "",
    val year: Int = 0,
    val description: String = "",
    val imageUrl: String = "",
    val thumbnail: String = "",
    val type: GameType = GameType.GAME,
    val rankingLatest: String = "",
    var rankingHistoryDates: List<String> = listOf(),
    var rankingHistoryPositions: List<String> = listOf(),
    var rankingCategories: List<String> = listOf(),
)