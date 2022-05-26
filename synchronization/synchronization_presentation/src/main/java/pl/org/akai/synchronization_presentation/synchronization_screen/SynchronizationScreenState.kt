package pl.org.akai.synchronization_presentation.synchronization_screen

import java.time.LocalDate

data class SynchronizationScreenState(
    val isSynchronizing: Boolean = false,
    val lastSychronized: LocalDate = LocalDate.now()
)