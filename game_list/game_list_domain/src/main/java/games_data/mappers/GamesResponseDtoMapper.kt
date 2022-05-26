package games_data.mappers

import data.local.entity.GameEntity
import data.remote.data.GameItemResponseDto
import pl.org.akai.game_list_domain.model.GameModel
import utils.GameType

fun GameItemResponseDto.toGameModel(): GameModel {
    return GameModel(
        id = id?.toIntOrNull() ?: 0,
        title = title ?: "",
        year = year?.toIntOrNull() ?: 0,
        description = comment ?: "",
        imageUrl = image ?: "",
        thumbnail = thumbnail ?: "",
        type = GameType.fromString(subtype ?: GameType.toString(GameType.GAME)),
        rankingLatest = stats?.rating?.value ?: "Not Ranked"
    )
}

fun GameItemResponseDto.toGameEntity(): GameEntity {
    return GameEntity(
        id = id?.toIntOrNull() ?: 0,
        title = title ?: "",
        year = year?.toIntOrNull() ?: 0,
        description = comment ?: "",
        imageUrl = image ?: "",
        thumbnail = thumbnail ?: "",
        type = subtype ?: "",
        rankingLatest = stats?.rating?.value ?: "Not Ranked",
        rankingCategoriesJson = ListTypeConverter.listToString(stats?.rating?.ranks?.map { it.category ?: "error" }),
        rankingHistoryDatesJson = ListTypeConverter.listToString(listOf())
    )
}