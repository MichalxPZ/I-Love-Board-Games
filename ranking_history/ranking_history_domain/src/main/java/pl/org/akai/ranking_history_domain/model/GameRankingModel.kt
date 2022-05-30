package pl.org.akai.ranking_history_domain.model

import utils.DateFormater
import utils.GameType
import java.time.LocalDate

data class GameRankingModel(

    val id: Int = 0,
    val title: String = "",
    val year: Int = 0,
    val description: String = "",
    val imageUrl: String = "",
    val thumbnail: String = "",
    val type: GameType = GameType.GAME,
    val rankingLatest: String = "0",
    var rankingHistoryDates: List<String> = listOf(DateFormater.dateToString(LocalDate.now()) ?: "1000-01-01"),
    var rankingHistoryPositions: List<String> = listOf(),
    var rankingCategories: List<String> = listOf(),
)