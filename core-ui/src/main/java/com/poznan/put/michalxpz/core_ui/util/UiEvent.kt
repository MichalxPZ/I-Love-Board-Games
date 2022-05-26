package com.poznan.put.michalxpz.core_ui.util

sealed class UiEvent {
    data class Navigate(val route: String) : UiEvent()
    data class ShowErrorSnackbar(val messageError: String) : UiEvent()
}
