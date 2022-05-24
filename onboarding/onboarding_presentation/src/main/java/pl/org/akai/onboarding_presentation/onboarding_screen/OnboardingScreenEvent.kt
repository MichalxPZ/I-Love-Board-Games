package pl.org.akai.onboarding_presentation.onboarding_screen

sealed class OnboardingScreenEvent {
    class OnNameTextFieldValueChanged(val value: String): OnboardingScreenEvent()
    object OnContinueClicked: OnboardingScreenEvent()
}
