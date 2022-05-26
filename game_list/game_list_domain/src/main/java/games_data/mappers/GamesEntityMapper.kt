package games_data.mappers

import data.local.entity.GameEntity
import pl.org.akai.game_list_domain.model.GameModel
import utils.GameType

fun GameEntity.toGameModel(): GameModel {
    return GameModel(
        id = id,
        title = title,
        year = year,
        description = description,
        imageUrl = imageUrl,
        thumbnail = thumbnail,
        type = GameType.fromString(type),
        rankingLatest = rankingLatest
    )
}