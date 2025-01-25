package com.deraesw.pokemoncards

import android.app.Application
import com.deraesw.pokemoncards.di.androidDataModule
import com.deraesw.pokemoncards.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class PcsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PcsApplication)
            modules(androidDataModule + appModules)
        }
    }
}
