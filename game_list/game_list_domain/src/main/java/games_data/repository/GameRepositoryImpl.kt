package games_data.repository

import android.util.Log
import data.local.GamesDataBase
import data.preferences.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mappers.toGameModel
import data.remote.Api
import data.remote.data.GameItemResponseDto
import mappers.toGameEntity
import model.GameModel
import pl.org.akai.game_list_domain.repository.GameRepository
import retrofit2.HttpException
import utils.GameType
import utils.Resource
import java.io.IOException

class GameRepositoryImpl(
    private val api: Api,
    private val db: GamesDataBase,
    private val preferences: Preferences
) : GameRepository {

    private val dao = db.dao


    override suspend fun getGames(
        fetchFromRemote: Boolean,
        query: String,
    ): Flow<Resource<List<GameModel>>> {
        return flow {
            emit( Resource.Loading(true) )

            val localGames = dao.searchGames(query)
            emit(Resource.Success(data = localGames.map { it.toGameModel() }))

            val isDbEmpty = localGames.isEmpty() && query.isBlank()
            val shouldLoadFromRemote = fetchFromRemote || isDbEmpty

            if (!shouldLoadFromRemote) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteGames = try {
                val games = mutableListOf<GameItemResponseDto>()

                preferences.loadUserName()?.let {
                    val result = api.getGamesForName(it)
                    result.item?.forEach {  gameResult ->
                        games.add(gameResult)
                    }
                } ?: emit(Resource.Error(message = "Invalid User Name"))

                preferences.loadUserName()?.let {
                    val result = api.getExpansionsForName(it)
                    result.item?.forEach {  gameResult ->
                        gameResult.subtype = GameType.toString(GameType.EXTENSION)
                        games.add(gameResult)
                    }
                } ?: emit(Resource.Error(message = "Invalid User Name"))

                games
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("IOException occurred"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Http Exception occurred\nMake sure your device is connected to the Internet"))
                emit(Resource.Loading(false))
                return@flow
            } catch (e: RuntimeException) {
                e.printStackTrace()
                emit(Resource.Error("There is no data for provided user name"))
                emit(Resource.Loading(false))
                return@flow
            }
            Log.i("REMOTE GAMES", remoteGames.toString())
            remoteGames.let { games ->
                dao.clearGames()
                dao.insertGame( games.map { it.toGameEntity() })

                emit(Resource.Success(data =
                dao.searchGames("")
                    .map { it.toGameModel() }
                ))
            }

            emit(Resource.Loading(false))
        }
    }

}