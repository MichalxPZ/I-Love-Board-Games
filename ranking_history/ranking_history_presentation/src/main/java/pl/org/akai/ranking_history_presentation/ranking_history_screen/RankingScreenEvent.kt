package pl.org.akai.ranking_history_presentation.ranking_history_screen

sealed class RankingScreenEvent {
    class OnInitGetGameFromDb(val id: Int): RankingScreenEvent()
}
