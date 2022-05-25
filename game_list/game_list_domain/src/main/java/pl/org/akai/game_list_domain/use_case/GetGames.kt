package pl.org.akai.game_list_domain.use_case

import kotlinx.coroutines.flow.Flow
import pl.org.akai.game_list_domain.model.GameModel
import pl.org.akai.game_list_domain.repository.GameRepository
import utils.Resource


class GetGames (
    private val gameRepository: GameRepository
) {

    suspend operator fun invoke(): Flow<Resource<List<GameModel>>> {
        return gameRepository.getGamesAndExtensions()
    }

}