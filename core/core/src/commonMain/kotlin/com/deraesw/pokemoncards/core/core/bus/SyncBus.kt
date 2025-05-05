package com.deraesw.pokemoncards.core.core.bus

import com.deraesw.pokemoncards.core.core.util.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SyncBus {
    private val _events = MutableSharedFlow<SyncEvent>()
    val events = _events.asSharedFlow()

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    fun sendEvent(event: SyncEvent) {
        coroutineScope.launch {
            _events.emit(event)
            Logger.debug("SyncBus", "$this sendEvent - $event")
        }
    }
}

sealed class SyncEvent {
    data object None : SyncEvent()
    data object StartCardSync : SyncEvent()
    data class CardSyncProgress(val message: String) : SyncEvent()
    data object CompleteCardSync : SyncEvent()
}
