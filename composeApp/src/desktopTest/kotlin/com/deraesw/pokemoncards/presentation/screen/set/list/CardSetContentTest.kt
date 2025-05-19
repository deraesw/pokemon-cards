package com.deraesw.pokemoncards.presentation.screen.set.list

import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.onSiblings
import androidx.compose.ui.test.performClick
import com.deraesw.pokemoncards.presentation.model.CardSetListItem
import org.junit.Rule
import kotlin.test.Test

class CardSetContentTest {

    @get:Rule
    val rule = createComposeRule()

    private val itemA = CardSetListItem(
        name = "name A",
        id = "1",
        formatedReleaseDate = "Jan 1, 2023",
        total = 100,
        series = "series A",
        imageSymbol = null
    )

    @Test
    fun `display no set found when no items are given`() {
        rule.setContent {
            CardSetContent()
        }

        rule
            .onNodeWithText("No card sets found")
            .assertIsDisplayed()
    }

    @Test
    fun `display do not display no set found when items are given`() {
        rule.setContent {
            CardSetContent(
                cardSetModelList = listOf(itemA)
            )
        }

        rule
            .onNodeWithText("No card sets found")
            .assertDoesNotExist()
    }

    @Test
    fun `display card set items when items are given`() {
        rule.setContent {
            CardSetContent(
                cardSetModelList = listOf(itemA)
            )
        }

        rule
            .onNodeWithText(itemA.name, useUnmergedTree = true)
            .assertIsDisplayed()

        rule
            .onNodeWithText(itemA.name, useUnmergedTree = true)
            .onSiblings()
            .assertAny(hasText(itemA.formatedReleaseDate))

        rule
            .onNodeWithText(itemA.name, useUnmergedTree = true)
            .onSiblings()
            .assertAny(hasText("${itemA.series} - ${itemA.total} cards"))
    }

    @Test
    fun `display 2 card items in the order`() {
        val itemB = CardSetListItem(
            name = "name B",
            id = "2",
            formatedReleaseDate = "Jan 10, 2023",
            total = 50,
            series = "series B",
            imageSymbol = null
        )

        rule.setContent {
            CardSetContent(
                cardSetModelList = listOf(itemA, itemB)
            )
        }

        rule.onRoot().onChild().onChildren().assertCountEquals(2)

        rule
            .onRoot()
            .onChild()
            .onChildren()[0]
            .assertTextContains("name A")
            .assertTextContains("Jan 1, 2023")
            .assertTextContains("series A - 100 cards")

        rule
            .onRoot()
            .onChild()
            .onChildren()[1]
            .assertTextContains("name B")
            .assertTextContains("Jan 10, 2023")
            .assertTextContains("series B - 50 cards")
    }

    @Test
    fun `call onCardSetClick when item is clicked`() {
        var idSelected = ""
        rule.setContent {
            CardSetContent(
                cardSetModelList = listOf(itemA),
                onCardSetClick = {
                    idSelected = it
                }
            )
        }

        rule
            .onRoot()
            .onChild()
            .onChildren()[0]
            .assertHasClickAction()
            .performClick()

        assert(idSelected == itemA.id)
    }
}
