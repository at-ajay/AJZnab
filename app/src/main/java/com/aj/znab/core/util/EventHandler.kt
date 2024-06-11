package com.aj.znab.core.util

import com.aj.znab.core.navigation.ZnabScreen
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

object EventHandler {
    private val _events = Channel<UIEvents>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(event: UIEvents) {
        _events.send(event)
    }
}

sealed class UIEvents {
    data class Toast(val message: String): UIEvents()
    data class Navigate(val toScreen: ZnabScreen): UIEvents()
    data class NavigateAndClearBackStack(val currentScreen: ZnabScreen, val toScreen: ZnabScreen): UIEvents()
}