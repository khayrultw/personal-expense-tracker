package com.khayrul.personalExpenseTracker.ui.utils

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

data class SnackbarAction(
    val name: String,
    val action: () -> Unit
)

data class SnackbarEvent(
    val message: String,
    val action: SnackbarAction? = null
)

object snackbarController {
    private val _events = Channel<SnackbarEvent>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(event: SnackbarEvent) {
        _events.send(event)
    }
}