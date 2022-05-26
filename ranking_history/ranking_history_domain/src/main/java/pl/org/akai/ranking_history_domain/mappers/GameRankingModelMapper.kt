package pl.org.akai.ranking_history_domain.mappers

import data.local.entity.GameEntity
import data.typeconverters.MapTypeConverter
import pl.org.akai.ranking_history_domain.model.GameRankingModel
import utils.GameType
import utils.ListTypeConverter

fun GameEntity.toGameRankingModel(): GameRankingModel {
    return GameRankingModel(
        id = id,
        title = title,
        year = year,
        description = description,
        imageUrl = imageUrl,
        thumbnail = thumbnail,
        type = GameType.fromString(type),
        rankingLatest = rankingLatest,
        rankingHistoryDates = ListTypeConverter.stringtoList(rankingHistoryDatesJson),
        rankingCategories = ListTypeConverter.stringtoList(rankingCategoriesJson),
        rankingHistoryPositions = ListTypeConverter.stringtoList(rankingHistoryPositionsJson)
    )
}


fun GameRankingModel.toGameEntity() : GameEntity {
    return GameEntity(
        id = id,
        title = title,
        year = year,
        description = description,
        imageUrl = imageUrl,
        thumbnail = thumbnail,
        type = GameType.toString(type),
        rankingLatest = rankingLatest,
        rankingHistoryDatesJson = ListTypeConverter.listToString(rankingHistoryDates),
        rankingCategoriesJson = ListTypeConverter.listToString(rankingCategories),
        rankingHistoryPositionsJson = ListTypeConverter.listToString(rankingHistoryPositions)
    )
}