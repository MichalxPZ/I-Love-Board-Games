package pl.org.akai.onboarding_presentation.onboarding_screen

sealed class OnboardingScreenEvent {
    object OnButtonPressed: OnboardingScreenEvent()
    class OnNameTextFieldValueChanged(val value: String): OnboardingScreenEvent()
}
