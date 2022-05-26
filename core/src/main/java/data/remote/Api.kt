package data.remote

import okhttp3.ResponseBody
import data.remote.data.GamesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/xmlapi2/collection")
    suspend fun getGamesForName(
        @Query("username") userName: String,
        @Query("stats") stats: Int = 1
    ) : GamesResponseDto

    @GET("/xmlapi2/collection")
    suspend fun getExpansionsForName(
        @Query("username") userName: String,
        @Query("stats") stats: Int = 1,
        @Query("subtype") expansion: String = "boardgameexpansion"
    ) : GamesResponseDto

    @GET("/xmlapi2/thing")
    suspend fun getAdditionalInfoWithId(
        @Query("id") id: Int,
        @Query("stats") stats: Int = 1
    ) : ResponseBody

    companion object {
        const val BASE_URL = "https://boardgamegeek.com"
    }
}