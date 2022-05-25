package pl.org.akai.game_list_domain.model

import java.time.LocalDate

data class GameModel(
    val id: Int,
    val title: String,
    val year: Int,
    val description: String,
    val imageUrl: String,
    val thumbnail: String,
    val type: GameType,
    val rankingHistory: HashMap<LocalDate, Int>,
    val rankingCategories: HashMap<String, Int>,
    val rankingLatest: String
)
