package com.deraesw.pokemoncards.data.repository

import com.deraesw.pokemoncards.data.database.DatabaseFactory
import com.deraesw.pokemoncards.data.database.PokemonCardDatabase
import com.deraesw.pokemoncards.data.database.createTestDriver
import com.deraesw.pokemoncards.model.CardSet
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNull
import kotlin.test.assertTrue

class CardSetRepositoryTest {

    private lateinit var repository: CardSetRepositoryImp

    private val cardSetA = CardSet(
        id = "g1",
        name = "Base",
        series = "Base",
        printedTotal = 102,
        total = 102,
        legalities = "Legal",
        releaseDate = "1999/01/09",
        updatedAt = "2020/08/14 09:35:00",
        imageSymbol = "https://images.pokemontcg.io/base1/symbol.png",
        imageLogo = "https://images.pokemontcg.io/base1/logo.png"
    )

    private val cardSetB = CardSet(
        id = "g2",
        name = "Base2",
        series = "Base2",
        printedTotal = 10,
        total = 10,
        legalities = "Legal2",
        releaseDate = "2000/01/09",
        updatedAt = "2024/08/14 09:35:00",
        imageSymbol = "https://images.pokemontcg.io/base2/symbol.png",
        imageLogo = "https://images.pokemontcg.io/base2/logo.png"
    )

    @BeforeTest
    fun setup() {
        val driver = createTestDriver()
        PokemonCardDatabase.Schema.create(driver)
        val databaseFactory = DatabaseFactory(driver)
        repository = CardSetRepositoryImp(databaseFactory)
    }

    @Test
    fun `getAllSets - return empty when there is no data`() = runTest {
        val data = repository.getAllSets()
        assertTrue { data.isEmpty() }
    }

    @Test
    fun `saveCardSetList - save the list of sets`() = runTest {
        val list = listOf(cardSetA)
        repository.saveCardSetList(list)

        val data = repository.getAllSets()
        assertTrue { data.size == 1 }
        assertTrue { data == list }
    }

    @Test
    fun `getAllSets - return the list of sets`() = runTest {
        val list = listOf(cardSetA, cardSetB)
        repository.saveCardSetList(list)
        val data = repository.getAllSets()
        assertTrue { data.size == 2 }
        assertTrue { data == list }
    }

    @Test
    fun `getSet - return the set when it exists`() = runTest {
        val list = listOf(cardSetA, cardSetB)
        repository.saveCardSetList(list)
        val data = repository.getSet(cardSetA.id)

        requireNotNull(data)
        assertTrue { data == cardSetA }
    }

    @Test
    fun `getSet - return null when the set does not exist`() = runTest {
        val list = listOf(cardSetB)
        repository.saveCardSetList(list)
        val data = repository.getSet(cardSetA.id)

        assertNull(data)
    }
}
