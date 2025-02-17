package com.deraesw.pokemoncards.di

import com.deraesw.pokemoncards.core.database.di.databaseModule
import com.deraesw.pokemoncards.core.network.di.networkModule
import com.deraesw.pokemoncards.data.repository.AppPreferencesRepository
import com.deraesw.pokemoncards.data.repository.AppPreferencesRepositoryImp
import com.deraesw.pokemoncards.data.repository.CardRepository
import com.deraesw.pokemoncards.data.repository.CardRepositoryImp
import com.deraesw.pokemoncards.data.repository.CardSetRepository
import com.deraesw.pokemoncards.data.repository.CardSetRepositoryImp
import com.deraesw.pokemoncards.domain.NetworkManager
import com.deraesw.pokemoncards.domain.NetworkManagerImp
import com.deraesw.pokemoncards.presentation.screen.card.list.CardListViewModel
import com.deraesw.pokemoncards.presentation.screen.set.detail.CardSetDetailViewModel
import com.deraesw.pokemoncards.presentation.screen.set.list.CardSetViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

fun pcsInitKoin(
    appDeclaration: KoinApplication.() -> Unit = {}
): KoinApplication {
    return startKoin {
        appDeclaration()
        modules(
            databaseModule,
            networkModule,
            dataModule,
            presentationModule,
            domainModule
        )
    }
}

private val dataModule = module {
    singleOf(::CardSetRepositoryImp) bind CardSetRepository::class
    singleOf(::AppPreferencesRepositoryImp) bind AppPreferencesRepository::class
    singleOf(::CardRepositoryImp) bind CardRepository::class
}

private val domainModule = module {
    singleOf(::NetworkManagerImp) bind NetworkManager::class
}

val presentationModule = module {
    viewModel { CardSetViewModel(get(), get()) }
    viewModel { CardSetDetailViewModel(get()) }
    viewModel { CardListViewModel(get()) }
}
