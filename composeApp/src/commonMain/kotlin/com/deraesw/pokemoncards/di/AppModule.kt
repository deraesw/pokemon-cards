package com.deraesw.pokemoncards.di

import com.deraesw.pokemoncards.core.core.bus.SyncBus
import com.deraesw.pokemoncards.core.data.di.dataModule
import com.deraesw.pokemoncards.core.database.di.databaseModule
import com.deraesw.pokemoncards.core.network.di.networkModule
import com.deraesw.pokemoncards.presentation.screen.card.list.CardListViewModel
import com.deraesw.pokemoncards.presentation.screen.set.detail.CardSetDetailViewModel
import com.deraesw.pokemoncards.presentation.screen.set.list.CardSetViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

fun pcsInitKoin(
    appDeclaration: KoinApplication.() -> Unit = {}
): KoinApplication {
    return startKoin {
        appDeclaration()
        modules(
            module { single { SyncBus() } },
            databaseModule,
            networkModule,
            dataModule,
            presentationModule
        )
    }
}

val presentationModule = module {
    viewModel { CardSetViewModel(get(), get()) }
    viewModel {
        if (it.size() == 0) {
            CardSetDetailViewModel(
                cardSetRepository = get(),
                providedSetId = null
            )
        } else {
            CardSetDetailViewModel(
                cardSetRepository = get(),
                providedSetId = get(parameters = { parametersOf(it[0]) })
            )
        }
    }
    viewModel { CardListViewModel(get(), get(), get()) }
}
