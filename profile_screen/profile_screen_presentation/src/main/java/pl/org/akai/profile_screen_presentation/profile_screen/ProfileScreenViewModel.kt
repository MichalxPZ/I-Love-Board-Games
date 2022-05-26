package pl.org.akai.profile_screen_presentation.profile_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.local.GamesDao
import data.local.GamesDataBase
import data.preferences.Preferences
import kotlinx.coroutines.launch
import utils.GameType
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val preferences: Preferences,
    private val dao: GamesDao
) : ViewModel() {

    var state by mutableStateOf(ProfileScreenState())
        private set

    init {
        viewModelScope.launch {
            val games = dao.searchGames("")
            val countGames = games.count { GameType.fromString(it.type) == GameType.GAME }
            val countExtensions = games.count { GameType.fromString(it.type) == GameType.EXTENSION }
            state = state.copy(
                userName = preferences.loadUserName() ?: "Error",
                gamesCount = countGames,
                extensionsCount = countExtensions,
                lastSynchDate = preferences.loadLastSynchDate() ?: LocalDate.now()
                )
        }
    }
}