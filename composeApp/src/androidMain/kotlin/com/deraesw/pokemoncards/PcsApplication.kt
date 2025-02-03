package com.deraesw.pokemoncards

import android.app.Application
import com.deraesw.pokemoncards.di.androidDataModule
import com.deraesw.pokemoncards.di.pcsInitKoin
import org.koin.android.ext.koin.androidContext

class PcsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        pcsInitKoin {
            androidContext(this@PcsApplication)
            modules(androidDataModule)
        }
//        startKoin {
//            androidContext(this@PcsApplication)
//            modules(androidDataModule + appModules)
//        }
    }
}
