package pl.org.akai.game_list_presentation.game_list_screen

import com.poznan.put.michalxpz.core_ui.util.utils.SortType
import utils.DialogType

sealed class GameListScreenEvent {
    class OnTextFieldValueChange(val value: String): GameListScreenEvent()
    class OnGameClicked(val route: String): GameListScreenEvent()
    class OnShowDialog(
        val message: String,
        val type: DialogType
    ) : GameListScreenEvent()
    object OnDismissDialog: GameListScreenEvent()
    class OnSortGames(val sortType: SortType): GameListScreenEvent()
    object OnSortButtonClick: GameListScreenEvent()
}
