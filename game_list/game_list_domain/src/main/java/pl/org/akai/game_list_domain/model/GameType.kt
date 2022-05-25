package pl.org.akai.game_list_domain.model

enum class GameType {
    GAME, EXTENSION;

    companion object {
        fun fromString(value: String): GameType {
            return when (value) {
                "boardgame" -> GameType.GAME
                "extensions" -> GameType.EXTENSION
                else -> GameType.GAME
            }
        }
    }
}