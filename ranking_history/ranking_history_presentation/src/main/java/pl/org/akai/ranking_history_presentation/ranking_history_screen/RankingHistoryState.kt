package pl.org.akai.ranking_history_presentation.ranking_history_screen

import pl.org.akai.ranking_history_domain.model.GameRankingModel

data class RankingHistoryState(
    val gameRankingModel: GameRankingModel = GameRankingModel()
)
