package pl.org.akai.synchronization_presentation.synchronization_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.preferences.Preferences
import kotlinx.coroutines.launch
import pl.org.akai.synchroznization_domain.synchronization.SynchronizeUseCase
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SynchronizationScreenViewModel @Inject constructor(
    private  val synchronize: SynchronizeUseCase,
    private val preferences: Preferences
) : ViewModel() {

    var state by mutableStateOf( SynchronizationScreenState() )
        private set

    init {
        viewModelScope.launch {
            val lastSynchDate = preferences.loadLastSynchDate()

            if (lastSynchDate == null) {
                state = state.copy(isSynchronizing = true)
                synchronize()
                preferences.saveLastSynchDate(LocalDate.now())
                state = state.copy(isSynchronizing = false, lastSychronized = LocalDate.now())
            } else {
                state = state.copy(lastSychronized = lastSynchDate)
            }
        }
        state = state.copy(lastSychronized = preferences.loadLastSynchDate() ?: LocalDate.now())
    }

    fun onEvent(event: SynchronizationScreenEvent) {
        when(event) {
            SynchronizationScreenEvent.OnSynchronizationButtonClick -> {
                viewModelScope.launch {
                    state = state.copy(isSynchronizing = true)
                    synchronize()
                    state = state.copy(isSynchronizing = false)
                }
            }
        }
    }
}