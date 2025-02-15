package com.deraesw.pokemoncards.di

import app.cash.sqldelight.db.SqlDriver
import com.deraesw.pokemoncards.core.network.di.networkModule
import com.deraesw.pokemoncards.data.database.DatabaseFactory
import com.deraesw.pokemoncards.data.database.createDriver
import com.deraesw.pokemoncards.data.repository.AppPreferencesRepository
import com.deraesw.pokemoncards.data.repository.AppPreferencesRepositoryImp
import com.deraesw.pokemoncards.data.repository.CardRepository
import com.deraesw.pokemoncards.data.repository.CardRepositoryImp
import com.deraesw.pokemoncards.data.repository.CardSetRepository
import com.deraesw.pokemoncards.data.repository.CardSetRepositoryImp
import com.deraesw.pokemoncards.domain.NetworkManager
import com.deraesw.pokemoncards.domain.NetworkManagerImp
import com.deraesw.pokemoncards.presentation.carddetail.CardSetDetailViewModel
import com.deraesw.pokemoncards.presentation.cardlist.CardListViewModel
import com.deraesw.pokemoncards.presentation.cardset.CardSetViewModel
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
        modules(networkModule, dataModule, presentationModule, domainModule)
    }
}

private val dataModule = module {
    single<SqlDriver> { createDriver() }
    single { DatabaseFactory(get()) }
    singleOf(::CardSetRepositoryImp) bind CardSetRepository::class
    singleOf(::AppPreferencesRepositoryImp) bind AppPreferencesRepository::class
    singleOf(::CardRepositoryImp) bind CardRepository::class
}

private val domainModule = module {
    singleOf(::NetworkManagerImp) bind NetworkManager::class
}

//private val networkModule = module {
//    single<HttpClientEngine> { createHttpEngine() }
//    single { NetworkClient(get()) }
//    singleOf(::PokemonCardApiServiceImp) bind PokemonCardApiService::class
//}

val presentationModule = module {
    viewModel { CardSetViewModel(get(), get()) }
    viewModel { CardSetDetailViewModel(get()) }
    viewModel { CardListViewModel(get()) }
}
