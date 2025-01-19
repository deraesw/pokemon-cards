package com.deraesw.pokemoncards.data.repository

import com.deraesw.pokemoncards.model.CardSet

interface CardSetRepository {
    fun getAllSets(): List<CardSet>
}