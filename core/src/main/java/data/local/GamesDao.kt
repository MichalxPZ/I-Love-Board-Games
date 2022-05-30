package data.local

import androidx.room.*
import data.local.entity.GameEntity

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(
        gameEntity: List<GameEntity>
    )

    @Query(
        """
            UPDATE gameentity
            SET
            rankingHistoryDatesJson = :rankingDates,
            rankingHistoryPositionsJson = :rankingPositions,
            rankingLatest = :rankingLatest
            WHERE
            id = :gameEntityId
        """
    )
    suspend fun updateGameRankings(
        gameEntityId: Int,
        rankingDates: String,
        rankingPositions: String,
        rankingLatest: String
    )

    @Query("DELETE FROM gameentity")
    suspend fun clearGames()

    @Query("""
        DELETE FROM gameentity
        Where id == :id
    """)
    suspend fun deleteGame(id: Int)


    @Query(
        """
            SELECT *
            FROM gameentity
            WHERE LOWER(title) LIKE '%' || LOWER(:query) || '%'
        """
    )
    suspend fun searchGames(query: String) : List<GameEntity>
}