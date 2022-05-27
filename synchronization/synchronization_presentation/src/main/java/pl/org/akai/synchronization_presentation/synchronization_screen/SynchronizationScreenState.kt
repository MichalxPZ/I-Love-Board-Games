package pl.org.akai.synchronization_presentation.synchronization_screen

import com.poznan.put.michalxpz.core.R
import utils.DialogType
import java.time.LocalDate

data class SynchronizationScreenState(
    val isSynchronizing: Boolean = false,
    val lastSychronized: LocalDate = LocalDate.now(),
    val showDialog: Boolean = false,
    val dialogTitle: String = "Error",
    val dialogDesc: String = "Some error occurred",
    val dialogImgId: Int = R.raw.error,
    val dialogType: DialogType = DialogType.ERROR
)