package pl.org.akai.synchroznization_domain.synchronization

import android.util.Log
import data.local.GamesDao
import data.preferences.Preferences
import data.remote.Api
import data.remote.data.GameItemResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mappers.toGameEntity
import retrofit2.HttpException
import utils.DateFormater
import utils.GameType
import utils.ListTypeConverter
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
            val localGames = dao.searchGames("")

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


            remoteGames.forEach { game ->

                if (localGames.isNotEmpty()) {

                    val localGamesMatchedById = localGames.filter { it.id.toString() == game.id }

                    if (localGamesMatchedById.isEmpty()) {
                        dao.insertGame(listOf(game.toGameEntity()))
                    } else {

                        val rankingPositionsListString =
                            localGamesMatchedById
                                .get(0).rankingHistoryPositionsJson

                        val rankingPositions = ListTypeConverter
                            .stringtoList(rankingPositionsListString)
                            .toMutableList()

                        rankingPositions.add(game.stats?.rating?.ranks?.filter { it.category == "boardgame" }
                            ?.get(0)?.value ?: "Not Ranked")

                        val rankingPositionsToString =
                            ListTypeConverter.listToString(rankingPositions)

                        val rankingDatesListString = localGamesMatchedById
                            .get(0).rankingHistoryDatesJson
                        val rankingDates = ListTypeConverter
                            .stringtoList(rankingDatesListString).toMutableList()

                        rankingDates.add(DateFormater.dateToString(LocalDate.now()) ?: "1000-01-01")

                        Log.i("LOCAL",
                            "${game.id}, Pos: $rankingPositionsListString, Dates: $rankingDatesListString")

                        val rankingDatesToString = ListTypeConverter.listToString(rankingDates)


                        val rankingLatest =
                            game.stats?.rating?.ranks?.filter { it.category == "boardgame" }
                                ?.get(0)?.value ?: "Not Ranked"
                        Log.i("UPDATE",
                            "${game.id}, Pos: $rankingPositionsToString, Dates: $rankingDatesToString")
                        dao.updateGameRankings(
                            gameEntityId = game.id?.toIntOrNull() ?: 0,
                            rankingPositions = rankingPositionsToString,
                            rankingDates = rankingDatesToString,
                            rankingLatest = rankingLatest
                        )
                    }
                } else {
                    dao.insertGame( listOf(game.toGameEntity()) )
                }
            }

            localGames.map { it.id }.forEach {
                if(!(remoteGames.map { it.id }.contains(it.toString()))) {
                    dao.deleteGame(it)
                }
            }

            emit(Resource.Success(remoteGames))
            emit(Resource.Loading(false))

        }
    }

    override suspend fun wipeOut() {
        dao.clearGames()
    }
}