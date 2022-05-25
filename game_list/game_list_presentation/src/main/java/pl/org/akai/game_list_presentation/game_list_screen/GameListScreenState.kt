package pl.org.akai.game_list_presentation.game_list_screen

import pl.org.akai.game_list_domain.model.GameModel

data class GameListScreenState(
    val textFieldValue: String = "",
    val isLoading: Boolean =  false,
    val games: List<GameModel> = emptyList(),
    val extensions: List<GameModel> = emptyList()
)