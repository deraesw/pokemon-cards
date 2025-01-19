package com.deraesw.pokemoncards.di

import com.deraesw.pokemoncards.presentation.cardset.CardSetViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { CardSetViewModel(get()) }
}
