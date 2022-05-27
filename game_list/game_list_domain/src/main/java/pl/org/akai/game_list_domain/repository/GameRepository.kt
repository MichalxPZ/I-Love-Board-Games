package pl.org.akai.game_list_domain.repository

import kotlinx.coroutines.flow.Flow
import model.GameModel
import utils.Resource

interface GameRepository {
    suspend fun getGames(
        fetchFromRemote: Boolean,
        query: String,
    ): Flow<Resource<List<GameModel>>>
}