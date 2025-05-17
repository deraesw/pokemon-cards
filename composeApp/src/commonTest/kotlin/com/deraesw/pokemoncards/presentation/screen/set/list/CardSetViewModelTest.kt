package com.deraesw.pokemoncards.presentation.screen.set.list

import com.deraesw.pokemoncards.core.core.model.CardSetModel
import com.deraesw.pokemoncards.core.core.model.SortData
import com.deraesw.pokemoncards.core.data.domain.NetworkManager
import com.deraesw.pokemoncards.core.data.repository.CardSetRepository
import com.deraesw.pokemoncards.util.MockFlowTestUtil
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.resetAnswers
import dev.mokkery.verify.VerifyMode.Companion.atLeast
import dev.mokkery.verify.VerifyMode.Companion.exactly
import dev.mokkery.verifySuspend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class CardSetViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var cardSetRepository: CardSetRepository
    private lateinit var networkManager: NetworkManager
    private lateinit var viewModel: CardSetViewModel

    private val mockCarsFlow: MockFlowTestUtil<List<CardSetModel>> = MockFlowTestUtil()

    private val itemA = CardSetModel(
        id = "1",
        name = "name",
        series = "series",
        total = 10,
        printedTotal = 10,
        releaseDate = "2020/02/07",
        updatedAt = "2020/08/14 09:35:00",
        legalities = "legalities",
        imageSymbol = "imageSymbol",
        imageLogo = "imageLogo"
    )

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        cardSetRepository = mock<CardSetRepository>()
        networkManager = mock<NetworkManager>()

        everySuspend { cardSetRepository.allCardSets(any()) } returns mockCarsFlow.flow

        viewModel = CardSetViewModel(
            cardSetRepository = cardSetRepository,
            networkManager = networkManager
        )
    }

    @AfterTest
    fun cleanup() {
        resetAnswers(cardSetRepository, networkManager)
        Dispatchers.resetMain()
    }

    @Test
    fun `initialSync calls networkManager initialSync`() = runTest {
        everySuspend { networkManager.initialSync() } returns Unit

        viewModel.initialSync()

        verifySuspend(exactly(1)) { networkManager.initialSync() }
    }

    @Test
    fun `uiState test empty state with default value`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        viewModel.uiState.value.apply {
            assertTrue { cardSetModelList.isEmpty() }
            assertTrue { selectedCardSetId == null }
            assertTrue { sortData == SortData.NAME }
            assertTrue { searchQuery.isEmpty() }
        }

        collectJob.cancel()
    }

    @Test
    fun `uiState call allCardSets from repository`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        this.advanceUntilIdle()
        verifySuspend(atLeast(1)) { cardSetRepository.allCardSets(any()) }
        collectJob.cancel()
    }

    @Test
    fun `setSortData updates sortData in uiState`() = runTest {
        everySuspend { cardSetRepository.allCardSets(any()) } returns flowOf(listOf(itemA))
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        viewModel.setSortData(SortData.CARD_COUNT)

        this.advanceUntilIdle()

        println(viewModel.uiState.value.sortData)
        assertTrue { viewModel.uiState.value.sortData == SortData.CARD_COUNT }

        collectJob.cancel()
    }

    @Test
    fun `updateSearchQuery updates searchQuery in uiState`() = runTest {
        everySuspend { cardSetRepository.allCardSets(any()) } returns flowOf(listOf(itemA))
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        viewModel.updateSearchQuery("na")

        this.advanceUntilIdle()
        assertTrue { viewModel.uiState.value.searchQuery == "na" }
        assertTrue { viewModel.uiState.value.cardSetModelList.isEmpty().not() }

        println(viewModel.uiState.value.cardSetModelList)

        collectJob.cancel()
    }

    @Test
    fun `updateSearchQuery with empty state when no results found`() = runTest {
        everySuspend { cardSetRepository.allCardSets(any()) } returns flowOf(listOf(itemA))
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        viewModel.updateSearchQuery("notfound")

        this.advanceUntilIdle()
        assertTrue { viewModel.uiState.value.searchQuery == "notfound" }
        assertTrue { viewModel.uiState.value.cardSetModelList.isEmpty() }

        collectJob.cancel()
    }
}
