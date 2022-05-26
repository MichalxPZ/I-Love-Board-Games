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
        }
    }


    private fun getGamesAndExtensions() {
        viewModelScope.launch {
            getGames(
                query = state.textFieldValue,
                fetchFromRemote = false
            ).collectIndexed { _, result ->
                when(result) {
                    is Resource.Error -> { _uiEvent.send(UiEvent.ShowErrorSnackbar(result.message ?: "Unexpected Error Occurred")) }
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