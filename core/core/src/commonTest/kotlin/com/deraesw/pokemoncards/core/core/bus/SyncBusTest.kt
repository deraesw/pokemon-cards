package com.deraesw.pokemoncards.core.core.bus

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SyncBusTest {

    private lateinit var bus: SyncBus

    @BeforeTest
    fun setup() {
        bus = SyncBus()
    }

    @Test
    fun `sendEvent - emits event`() = runTest {
        bus.events.test {
            bus.sendEvent(SyncEvent.None)
            assertEquals(this.awaitItem(), SyncEvent.None)
            this.cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `events - events are sent in the order they were received`() = runTest {
        bus.events.test {
            bus.sendEvent(SyncEvent.None)
            bus.sendEvent(SyncEvent.StartCardSync)

            assertEquals(this.awaitItem(), SyncEvent.None)
            assertEquals(this.awaitItem(), SyncEvent.StartCardSync)
            this.cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `events - duplicate events are sent in the order they were received`() = runTest {
        bus.events.test {
            bus.sendEvent(SyncEvent.None)
            bus.sendEvent(SyncEvent.None)

            assertEquals(this.awaitItem(), SyncEvent.None)
            assertEquals(this.awaitItem(), SyncEvent.None)

            this.cancelAndIgnoreRemainingEvents()
        }
    }
}
