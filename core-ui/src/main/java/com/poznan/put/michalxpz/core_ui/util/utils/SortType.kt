package com.poznan.put.michalxpz.core_ui.util.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

enum class SortType {
    ALPHABETICAL_DESC, ALPHABETICAL_ASC, YEAR_ASC, YEAR_DESC, RANKING_ASC, RANKING_DESC, DEFAULT;

    companion object {
        fun getString(sortType: SortType): String {
            return when(sortType) {
                ALPHABETICAL_DESC -> "Alphabetical"
                ALPHABETICAL_ASC -> "Alphabetical"
                YEAR_ASC -> "Year"
                YEAR_DESC -> "Year"
                RANKING_ASC -> "Ranking"
                RANKING_DESC -> "Ranking"
                DEFAULT -> "Default"
            }
        }

        fun getImage(sortType: SortType): ImageVector {
            return when(sortType) {
                ALPHABETICAL_DESC -> Icons.Default.KeyboardArrowDown
                ALPHABETICAL_ASC -> Icons.Default.KeyboardArrowUp
                YEAR_ASC -> Icons.Default.KeyboardArrowUp
                YEAR_DESC -> Icons.Default.KeyboardArrowDown
                RANKING_ASC -> Icons.Default.KeyboardArrowUp
                RANKING_DESC -> Icons.Default.KeyboardArrowDown
                DEFAULT -> Icons.Default.Menu
            }
        }
    }
}