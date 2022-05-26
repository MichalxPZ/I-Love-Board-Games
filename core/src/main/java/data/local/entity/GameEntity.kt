package data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameEntity (
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var title: String,
    var year: Int,
    var description: String,
    var imageUrl: String,
    var thumbnail: String,
    var type: String,
    var rankingHistoryDatesJson: String,
    var rankingHistoryPositionsJson: String,
    var rankingCategoriesJson: String,
    var rankingLatest: String
)