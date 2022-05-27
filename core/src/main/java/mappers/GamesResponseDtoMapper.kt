package mappers

import data.local.entity.GameEntity
import data.remote.data.GameItemResponseDto
import model.GameModel
import utils.DateFormater
import utils.GameType
import utils.ListTypeConverter
import java.time.LocalDate

fun GameItemResponseDto.toGameModel(): GameModel {
    return GameModel(
        id = id?.toIntOrNull() ?: 0,
        title = title ?: "",
        year = year?.toIntOrNull() ?: 0,
        description = comment ?: "",
        imageUrl = image ?: "",
        thumbnail = thumbnail ?: "",
        type = GameType.fromString(subtype ?: GameType.toString(GameType.GAME)),
        rankingLatest = stats?.rating?.ranks?.filter { it.category == "boardgame" }?.get(0)?.value ?: "Not Ranked"
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
        rankingLatest = stats?.rating?.ranks?.filter { it.category == "boardgame" }?.get(0)?.value ?: "Not Ranked",
        rankingCategoriesJson = ListTypeConverter.listToString(stats?.rating?.ranks?.map { it.category ?: "error" }),
        rankingHistoryDatesJson = ListTypeConverter.listToString(listOf(DateFormater.dateToString(LocalDate.now()) ?: "1000-01-01")),
        rankingHistoryPositionsJson = ListTypeConverter.listToString(listOf(stats?.rating?.ranks?.filter { it.category == "boardgame" }?.get(0)?.value ?: "Not Ranked"))
    )
}