package pl.org.akai.game_list_presentation.game_list_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import pl.org.akai.game_list_domain.model.GameType
import pl.org.akai.game_list_domain.repository.GameRepository
import pl.org.akai.game_list_domain.use_case.GetGames
import utils.Resource
import javax.inject.Inject

@HiltViewModel
class GameListScreenViewModel @Inject constructor(
    private val getGames: GetGames
) : ViewModel() {

    var state by mutableStateOf( GameListScreenState() )
        private set

    init {
        getGamesAndExtensions()
    }

    fun onEvent(event: GameListScreenEvent) {
        when(event) {
            is GameListScreenEvent.OnTextFieldValueChange -> { state = state.copy(textFieldValue = event.value) }
        }
    }


    private fun getGamesAndExtensions() {
        viewModelScope.launch {
            getGames().collectIndexed { _, result ->
                when(result) {
                    is Resource.Error -> {}
                    is Resource.Loading -> { state = state.copy(isLoading = result.isLoading) }
                    is Resource.Success -> {
                        Log.i("RESPONSE_VIEW_MODEL", result.data.toString())
                        result.data?.let { gameModels ->
                            state = state.copy(games = gameModels.filter { it.type == GameType.GAME }, extensions = gameModels.filter { it.type == GameType.EXTENSION }, isLoading = false)
                        }
                        Log.i("RESPONSE_VIEW_MODEL", state.toString())
                    }
                }
            }
        }
    }
}