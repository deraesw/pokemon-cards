package com.deraesw.pokemoncards

import android.app.Application
import com.deraesw.pokemoncards.core.core.util.Logger
import com.deraesw.pokemoncards.core.database.factory.DriverFactory
import com.deraesw.pokemoncards.di.pcsInitKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class PcsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Logger.debug("PcsApplication", "onCreate")
        pcsInitKoin {
            androidContext(this@PcsApplication)
            modules(
                module {
                    single { DriverFactory(get()) }
                }
            )
        }
    }
}
