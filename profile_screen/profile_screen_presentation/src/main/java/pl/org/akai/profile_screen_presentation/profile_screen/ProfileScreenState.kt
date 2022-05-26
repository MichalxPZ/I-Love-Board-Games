package pl.org.akai.profile_screen_presentation.profile_screen

import java.time.LocalDate

data class ProfileScreenState(
    val userName: String = "",
    val gamesCount: Int = 0,
    val extensionsCount: Int = 0,
    val lastSynchDate: LocalDate = LocalDate.now()
)