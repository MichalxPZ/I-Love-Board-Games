package pl.org.akai.game_list_presentation.game_list_screen.components

import com.poznan.put.michalxpz.core_ui.util.utils.SortType


data class ExpandableOption(
    val sortType: SortType,
    val selected: Boolean = false
)