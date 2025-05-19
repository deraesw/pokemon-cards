package com.deraesw.pokemoncards.presentation.screen.set.detail

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToString
import com.deraesw.pokemoncards.presentation.model.CardSetDetail
import org.junit.Rule
import org.junit.Test

class CardSetDetailContentLargeTest {
    @get:Rule
    val rule = createComposeRule()

    private val itemA = CardSetDetail(
        id = "itemA_id",
        name = "itemA_name",
        series = "itemA_series",
        total = 100,
        printedTotal = 200,
        updatedAt = "itemA_updatedAt",
        formatedUpdatedAt = "itemA_formatedUpdatedAt",
        formatedReleaseDate = "itemA_formatedReleaseDate",
        imageLogo = "itemA_imageLogo"
    )

    @Test
    fun `display card set detail when set is given`() {
        rule.setContent {
            CardSetDetailContentLarge(set = itemA)
        }

        println(rule.onRoot().printToString())

        rule.onNodeWithText(itemA.name).assertIsDisplayed()
        rule.onNodeWithText(itemA.series).assertIsDisplayed()

        rule.onNodeWithText("Last updated at").assertIsDisplayed()
        rule.onNodeWithText(itemA.formatedUpdatedAt).assertIsDisplayed()

        rule.onNodeWithText("Release date: " + itemA.formatedReleaseDate).assertIsDisplayed()

        rule.onNodeWithText("Total cards").assertIsDisplayed()
        rule.onNodeWithText(itemA.total.toString()).assertIsDisplayed()

        rule.onNodeWithText("Printed cards").assertIsDisplayed()
        rule.onNodeWithText(itemA.printedTotal.toString()).assertIsDisplayed()

        rule.onNodeWithContentDescription("Expand button").assertIsDisplayed()
    }

    @Test
    fun `display card set detail when set is given and click expand`() {
        rule.setContent {
            CardSetDetailContentLarge(set = itemA)
        }

        rule.onNodeWithContentDescription("Expand button").assertHasClickAction()
        rule.onNodeWithContentDescription("Expand button").performClick()

        rule.onNodeWithText(itemA.name).assertIsDisplayed()
        rule.onNodeWithText(itemA.series).assertIsDisplayed()

        rule.onNodeWithText("Last updated at").assertIsDisplayed()
        rule.onNodeWithText(itemA.formatedUpdatedAt).assertIsDisplayed()

        rule.onNodeWithText("Release date: " + itemA.formatedReleaseDate).assertIsNotDisplayed()

        rule.onNodeWithText("Total cards").assertIsNotDisplayed()
        rule.onNodeWithText(itemA.total.toString()).assertIsNotDisplayed()

        rule.onNodeWithText("Printed cards").assertIsNotDisplayed()
        rule.onNodeWithText(itemA.printedTotal.toString()).assertIsNotDisplayed()
    }
}