package pl.org.akai.onboarding_presentation.onboarding_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.poznan.put.michalxpz.core_ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import data.preferences.Preferences
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import pl.org.akai.onboarding_domain.use_case.ValidateUserNameUseCase
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val validateUserNameUseCase: ValidateUserNameUseCase,
    private val preferences: Preferences
) : ViewModel() {

    var state by mutableStateOf(OnboardingState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: OnboardingScreenEvent) {
        when (event) {
            is OnboardingScreenEvent.OnNameTextFieldValueChanged -> {
                state = state.copy(userName = event.value)
                state = if (validateUserNameUseCase(state.userName)) {
                    state.copy(buttonActive = true)
                } else state.copy(buttonActive = false)
            }
            OnboardingScreenEvent.OnContinueClicked -> {
                preferences.saveUserName(state.userName)
                preferences.saveShouldShowOnboarding(false)
            }
        }
    }
}