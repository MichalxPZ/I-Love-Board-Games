package pl.org.akai.game_list_presentation.game_list_screen

import model.GameModel
import com.poznan.put.michalxpz.core.R
import com.poznan.put.michalxpz.core_ui.util.utils.SortType
import pl.org.akai.game_list_presentation.game_list_screen.components.ExpandableOption

data class GameListScreenState(
    val textFieldValue: String = "",
    val isLoading: Boolean =  false,
    val games: List<GameModel> = emptyList(),
    val extensions: List<GameModel> = emptyList(),
    val showErrorDialog: Boolean = false,
    val dialogTitle: String = "Error",
    val dialogDesc: String = "Some error occurred",
    val dialogImgId: Int = R.raw.error,
    val sortType: SortType = SortType.DEFAULT,
    val sortOptions: List<ExpandableOption> = listOf(),
    val sortButtonExpanded: Boolean = false
)