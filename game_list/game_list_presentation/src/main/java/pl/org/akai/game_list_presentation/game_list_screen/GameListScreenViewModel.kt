package pl.org.akai.game_list_presentation.game_list_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poznan.put.michalxpz.core_ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import utils.GameType
import pl.org.akai.game_list_domain.use_case.GetGames
import com.poznan.put.michalxpz.core.R
import com.poznan.put.michalxpz.core_ui.util.utils.SortType
import pl.org.akai.game_list_presentation.game_list_screen.components.ExpandableOption
import utils.DialogType
import utils.Resource
import javax.inject.Inject

@HiltViewModel
class GameListScreenViewModel @Inject constructor(
    private val getGames: GetGames,
) : ViewModel() {

    var state by mutableStateOf( GameListScreenState() )
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getGamesAndExtensions()
        state = state.copy(
            sortOptions =
            SortType.values().map { sortType ->
                ExpandableOption(
                    sortType = sortType
                )
            }
        )
    }

    fun onEvent(event: GameListScreenEvent) {
        when(event) {
            is GameListScreenEvent.OnTextFieldValueChange -> {
                state = state.copy(textFieldValue = event.value)
                getGamesAndExtensions()
            }
            is GameListScreenEvent.OnGameClicked -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Navigate(event.route))
                }
            }
            GameListScreenEvent.OnDismissDialog -> {
                state = state.copy(showErrorDialog = false)
            }
            is GameListScreenEvent.OnShowDialog -> {
                state = when(event.type) {
                    DialogType.ERROR -> {
                        state.copy(dialogTitle = "Error", dialogDesc = event.message, dialogImgId = R.raw.error, showErrorDialog = true)
                    }
                    DialogType.SUCCESS -> {
                        state.copy(dialogTitle = "Success", dialogDesc = event.message, dialogImgId = R.raw.success, showErrorDialog = true)
                    }
                    DialogType.INFO -> {
                        state.copy(dialogTitle = "Info", dialogDesc = event.message, dialogImgId = R.raw.info, showErrorDialog = true)
                    }
                }
            }
            is GameListScreenEvent.OnSortGames -> {
                state = state.copy(sortType = event.sortType)
                state = when(event.sortType) {
                    SortType.ALPHABETICAL_DESC -> {
                        state.copy(games = state.games.sortedBy { it.title }, extensions = state.extensions.sortedBy { it.title })
                    }
                    SortType.ALPHABETICAL_ASC -> {
                        state.copy(games = state.games.sortedBy { it.title }.reversed(), extensions = state.extensions.sortedBy { it.title }.reversed())
                    }
                    SortType.YEAR_ASC -> {
                        state.copy(games = state.games.sortedBy { it.year }, extensions = state.extensions.sortedBy { it.year })
                    }
                    SortType.YEAR_DESC -> {
                        state.copy(games = state.games.sortedBy { it.year }.reversed(), extensions = state.extensions.sortedBy { it.year }.reversed())
                    }
                    SortType.RANKING_ASC -> {
                        state.copy(games = state.games.sortedBy { it.rankingLatest.toIntOrNull() }, extensions = state.extensions.sortedBy { it.rankingLatest.toIntOrNull() })
                    }
                    SortType.RANKING_DESC -> {
                        state.copy(games = state.games.sortedBy { it.rankingLatest.toIntOrNull() }.reversed(), extensions = state.extensions.sortedBy { it.rankingLatest.toIntOrNull() }.reversed())
                    }
                    SortType.DEFAULT -> {
                        state.copy(games = state.games.sortedBy { it.id }, extensions = state.extensions.sortedBy { it.id })
                    }
                }
            }
            GameListScreenEvent.OnSortButtonClick -> { state = state.copy(sortButtonExpanded = !state.sortButtonExpanded)}
        }
    }


    private fun getGamesAndExtensions() {
        viewModelScope.launch {
            getGames(
                query = state.textFieldValue,
                fetchFromRemote = false
            ).collectIndexed { _, result ->
                when(result) {
                    is Resource.Error -> {
                        state = state.copy(showErrorDialog = true)
                        _uiEvent.send(UiEvent.ShowErrorDialog(result.message ?: "Unexpected Error Occurred"))
                    }
                    is Resource.Loading -> { state = state.copy(isLoading = result.isLoading) }
                    is Resource.Success -> {
                        result.data?.let { gameModels ->
                            state = state.copy(games = gameModels.filter { it.type == GameType.GAME }, extensions = gameModels.filter { it.type == GameType.EXTENSION }, isLoading = false)
                        }
                    }
                }
            }
        }
    }
}