package com.poznan.put.michalxpz.core_ui.util

sealed class UiEvent {
    data class Navigate(val route: String) : UiEvent()
    data class ShowErrorDialog(val messageError: String) : UiEvent()
    data class ShowSuccessDialog(val messageError: String) : UiEvent()
    data class ShowInfoDialog(val messageError: String) : UiEvent()
}
