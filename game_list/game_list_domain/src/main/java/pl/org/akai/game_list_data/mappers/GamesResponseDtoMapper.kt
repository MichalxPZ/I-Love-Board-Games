package pl.org.akai.game_list_data.mappers

import pl.org.akai.game_list_data.remote.data.GameItemResponse
import pl.org.akai.game_list_domain.model.GameModel
import pl.org.akai.game_list_domain.model.GameType

fun GameItemResponse.toGameModel(): GameModel {
    return GameModel(
        id = id.toIntOrNull() ?: 0,
        title = title ?: "",
        year = year?.toIntOrNull() ?: 0,
        description = comment ?: "",
        imageUrl = image ?: "",
        thumbnail = thumbnail ?: "",
        type = GameType.fromString(subtype),
        rankingHistory = hashMapOf(),
        rankingCategories = hashMapOf(),
        rankingLatest = stats?.stats?.pos ?: "0"
    )
}