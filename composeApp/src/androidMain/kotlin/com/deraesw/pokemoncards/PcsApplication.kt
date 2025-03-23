package com.deraesw.pokemoncards

import android.app.Application
import com.deraesw.pokemoncards.core.database.factory.DriverFactory
import com.deraesw.pokemoncards.di.pcsInitKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class PcsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        pcsInitKoin {
            androidContext(this@PcsApplication)
            module {
                single { DriverFactory(get()) }
            }
//            modules(androidDataModule)
        }
//        startKoin {
//            androidContext(this@PcsApplication)
//            modules(androidDataModule + appModules)
//        }
    }
}
