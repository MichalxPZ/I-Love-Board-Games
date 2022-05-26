package data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import data.local.entity.GameEntity

@Database(
    entities = [GameEntity::class],
    version = 1
)
abstract class GamesDataBase : RoomDatabase() {
    abstract val dao: GamesDao
}