package utils

enum class GameType {
    GAME, EXTENSION;

    companion object {
        fun fromString(value: String): GameType {
            return when (value) {
                "boardgame" -> GAME
                "boardgameexpansion" -> EXTENSION
                else -> GAME
            }
        }

        fun toString(value: GameType): String {
            return when (value) {
                GAME -> "boardgame"
                EXTENSION -> "boardgameexpansion"
            }
        }
    }
}