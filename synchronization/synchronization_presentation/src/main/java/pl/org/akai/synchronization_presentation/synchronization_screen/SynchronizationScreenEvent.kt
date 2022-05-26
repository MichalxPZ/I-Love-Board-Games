package pl.org.akai.synchronization_presentation.synchronization_screen

sealed class SynchronizationScreenEvent {
    object OnSynchronizationButtonClick : SynchronizationScreenEvent()
}