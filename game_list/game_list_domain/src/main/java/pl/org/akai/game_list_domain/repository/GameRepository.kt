package pl.org.akai.game_list_domain.repository

import kotlinx.coroutines.flow.Flow
import pl.org.akai.game_list_domain.model.GameModel
import utils.Resource

interface GameRepository {
    suspend fun getGamesAndExtensions(): Flow<Resource<List<GameModel>>>
}