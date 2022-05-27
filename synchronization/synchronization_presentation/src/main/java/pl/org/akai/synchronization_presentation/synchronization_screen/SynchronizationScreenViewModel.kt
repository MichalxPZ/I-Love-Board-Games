package pl.org.akai.synchronization_presentation.synchronization_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poznan.put.michalxpz.core.R
import com.poznan.put.michalxpz.core_ui.util.UiEvent
import com.poznan.put.michalxpz.core_ui.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import data.preferences.Preferences
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.org.akai.synchroznization_domain.synchronization.SynchronizeUseCase
import pl.org.akai.synchroznization_domain.synchronization.WipeOutUseCase
import utils.DialogType
import utils.Resource
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class SynchronizationScreenViewModel @Inject constructor(
    private  val synchronize: SynchronizeUseCase,
    private  val wipeOut: WipeOutUseCase,
    private val preferences: Preferences
) : ViewModel() {

    var state by mutableStateOf( SynchronizationScreenState() )
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            val lastSynchDate = preferences.loadLastSynchDate()

            if (lastSynchDate == null) {
                state = state.copy(isSynchronizing = true)
                synchronizeDbWithApi()
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
                handleShowingSynchRequestDialog()
            }
            is SynchronizationScreenEvent.OnShowDialog -> {
                state = when(event.type) {
                    DialogType.ERROR -> {
                        state.copy(dialogTitle = "Error", dialogDesc = event.message, dialogImgId = R.raw.error, showDialog = true, dialogType = DialogType.ERROR)
                    }
                    DialogType.SUCCESS -> {
                        state.copy(dialogTitle = "Success", dialogDesc = event.message, dialogImgId = R.raw.success, showDialog = true, dialogType = DialogType.SUCCESS)
                    }
                    DialogType.INFO -> {
                        state.copy(dialogTitle = "Info", dialogDesc = event.message, dialogImgId = R.raw.info, showDialog = true, dialogType = DialogType.INFO)
                    }
                }
            }
            SynchronizationScreenEvent.OnDialogDismiss -> { state = state.copy(showDialog = false)}
            is SynchronizationScreenEvent.OnSynchAccepted -> {
                if (event.type == DialogType.ERROR) {
                    state = state.copy(showDialog = false)
                } else {
                    state = state.copy(showDialog = false)
                    synchronizeDbWithApi()
                }
            }
            SynchronizationScreenEvent.OnClearDataClicked -> {
                viewModelScope.launch {
                    wipeOut()
                    preferences.saveShouldShowOnboarding(true)
                    preferences.saveLastSynchDate(LocalDate.MIN)
                }
            }
        }
    }

    private fun synchronizeDbWithApi() {
        viewModelScope.launch {
            synchronize().collectIndexed { _, result ->
                when(result) {
                    is Resource.Error -> {
                        _uiEvent.send(UiEvent.ShowErrorDialog(result.message ?: "Unexpected Error Occurred"))
                        state = state.copy( dialogType = DialogType.ERROR)
                    }
                    is Resource.Loading -> { state = state.copy(isSynchronizing = result.isLoading) }
                    is Resource.Success -> {
                        result.data?.let { gameModels ->
                            preferences.saveLastSynchDate(LocalDate.now())
                            state = state.copy(lastSychronized = LocalDate.now())
                        }
                    }
                }
            }
        }
    }

    private fun handleShowingSynchRequestDialog() {
        if (ChronoUnit.DAYS.between(LocalDate.now(), preferences.loadLastSynchDate()) < 1) {
            state = state.copy(dialogTitle = "Info", dialogDesc = "Last Synchronization was less then 24h ago.\nDo you want to proceed?", dialogImgId = R.raw.info, showDialog = true, dialogType = DialogType.INFO)
        }
    }
}