package pl.org.akai.synchroznization_domain.synchronization

import android.util.Log
import data.local.GamesDao
import data.preferences.Preferences
import data.remote.Api
import data.remote.data.GameItemResponseDto
import data.typeconverters.MapTypeConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import utils.Resource
import java.io.IOException
import java.time.LocalDate

class SynchronizationRepositoryImpl(
    private val api: Api,
    private val dao: GamesDao,
    private val preferences: Preferences
): SynchronizationRepository {

    override suspend fun synchronize(): Flow<Resource<List<GameItemResponseDto>>> {
        return flow {
            emit( Resource.Loading(true) )

            val remoteGames = try {
                val games = mutableListOf<GameItemResponseDto>()

                preferences.loadUserName()?.let {
                    val result = api.getGamesForName(it)
                    result.item?.forEach {  gameResult ->
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

            val localGames = dao.searchGames("")

            remoteGames.forEach { game ->
                val rankingString= localGames.filter { it.id.toString() == game.id }.get(0).rankingLatest
                val ranking = MapTypeConverter
                    .stringToMap(rankingString).apply {
                        toMutableMap()
                            .put(LocalDate.now().toString(), game.stats?.rating?.ranks?.get(0)?.pos?.toInt() ?: 0)
                    }
                val rankingToString = MapTypeConverter.mapToString(ranking)
                dao.updateGameRankings(
                    gameEntityId = game.id?.toIntOrNull() ?: 0,
                    ranking = rankingToString
                )
            }

            emit(Resource.Success(remoteGames))
            emit(Resource.Loading(false))

        }
    }
}