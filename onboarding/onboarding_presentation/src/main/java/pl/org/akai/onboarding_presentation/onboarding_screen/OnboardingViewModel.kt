package pl.org.akai.onboarding_presentation.onboarding_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.poznan.put.michalxpz.core_ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(OnboardingState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: OnboardingScreenEvent) {
        when(event) {
            OnboardingScreenEvent.OnButtonPressed -> {
                //todo validate, update sharedPrefs and navigate
            }
            is OnboardingScreenEvent.OnNameTextFieldValueChanged -> {
                state = state.copy(userName = event.value)
            }
        }
    }

}