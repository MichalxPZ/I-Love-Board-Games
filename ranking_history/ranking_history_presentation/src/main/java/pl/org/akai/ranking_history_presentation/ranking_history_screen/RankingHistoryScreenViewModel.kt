package pl.org.akai.ranking_history_presentation.ranking_history_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.local.GamesDao
import data.local.entity.GameEntity
import kotlinx.coroutines.launch
import pl.org.akai.ranking_history_domain.mappers.toGameRankingModel
import pl.org.akai.ranking_history_domain.model.GameRankingModel
import javax.inject.Inject

@HiltViewModel
class RankingHistoryScreenViewModel @Inject constructor(
    private val dao: GamesDao
) : ViewModel() {

    var state by mutableStateOf( RankingHistoryState() )
        private set

    fun onEvent(event: RankingScreenEvent) {
        when(event) {
            is RankingScreenEvent.OnInitGetGameFromDb -> {
                viewModelScope.launch {
                    state = state.copy(gameRankingModel = getGameInfoFromDb(event.id))
                }
            }
        }

    }

    private suspend fun getGameInfoFromDb(id: Int): GameRankingModel {
       return dao.searchGames("")
           .filter { it.id == id }
           .get(0)
           .toGameRankingModel()
    }

}
