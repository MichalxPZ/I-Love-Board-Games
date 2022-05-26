package pl.org.akai.game_list_presentation.game_list_screen

sealed class GameListScreenEvent {
    class OnTextFieldValueChange(val value: String): GameListScreenEvent()
    class OnGameClicked(val route: String): GameListScreenEvent()
}
