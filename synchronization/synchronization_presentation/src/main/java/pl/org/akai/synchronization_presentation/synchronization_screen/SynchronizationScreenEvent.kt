package pl.org.akai.synchronization_presentation.synchronization_screen

import utils.DialogType

sealed class SynchronizationScreenEvent {
    object OnSynchronizationButtonClick : SynchronizationScreenEvent()
    class OnShowDialog(
        val message: String,
        val type: DialogType
    ) : SynchronizationScreenEvent()
    object OnDialogDismiss: SynchronizationScreenEvent()
    class OnSynchAccepted(val type: DialogType): SynchronizationScreenEvent()
    object OnClearDataClicked: SynchronizationScreenEvent()
}