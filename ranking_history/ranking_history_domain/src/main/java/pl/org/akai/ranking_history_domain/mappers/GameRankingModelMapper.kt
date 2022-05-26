package pl.org.akai.ranking_history_domain.mappers

import data.local.entity.GameEntity
import data.typeconverters.MapTypeConverter
import pl.org.akai.ranking_history_domain.model.GameRankingModel
import utils.GameType

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
        rankingHistoryDates = MapTypeConverter.stringToMap(rankingHistoryDatesJson),
        rankingCategories = MapTypeConverter.stringToMap(rankingCategoriesJson)
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
        rankingHistoryDatesJson = MapTypeConverter.mapToString(rankingHistoryDates),
        rankingCategoriesJson = MapTypeConverter.mapToString(rankingCategories)
    )
}