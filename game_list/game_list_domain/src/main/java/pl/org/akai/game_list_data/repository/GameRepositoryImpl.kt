package pl.org.akai.game_list_data.repository

import android.util.Log
import data.preferences.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.org.akai.game_list_data.mappers.toGameModel
import pl.org.akai.game_list_data.remote.Api
import pl.org.akai.game_list_domain.model.GameModel
import pl.org.akai.game_list_domain.repository.GameRepository
import retrofit2.HttpException
import utils.Resource
import java.io.IOException

class GameRepositoryImpl(
    private val api: Api,
    private val preferences: Preferences
) : GameRepository {
    override suspend fun getGamesAndExtensions(): Flow<Resource<List<GameModel>>> {
        return flow {
            emit( Resource.Loading(true) )
            try {
                preferences.loadUserName()?.let {
                    val result = api.getBooksForName(it)
                    Log.i("RESPONSE", result.toString())
                    emit(Resource.Success(data = result.item?.map { it.toGameModel() }))
                } ?: emit(Resource.Error(message = "Invalid User Name"))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(e.stackTraceToString()))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(e.stackTraceToString()))
                return@flow
            }
            emit(Resource.Loading(false))
        }
    }
}