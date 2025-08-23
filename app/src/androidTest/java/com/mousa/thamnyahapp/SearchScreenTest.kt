package com.mousa.thamnyahapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.mousa.thamnyahapp.presentation.screen.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SearchScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun typingQuery_showsLoading() {
        composeRule.onNodeWithTag("SearchBar").performTextInput("Test")
        composeRule.onNodeWithTag("Loading").assertExists()
    }

    @Test
    fun typingQuery_showsResults() {
        composeRule.onNodeWithTag("SearchBar").performTextInput("Test")

        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithText("Test").fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onNodeWithText("Test").assertIsDisplayed()
    }

    @Test
    fun typingQuery_showsEmptyView() {
        composeRule.onNodeWithTag("SearchBar").performTextInput("NoData")

        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithTag("EmptyView").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("EmptyView").assertIsDisplayed()
    }

    @Test
    fun typingQuery_showsErrorView() {
        composeRule.onNodeWithTag("SearchBar").performTextInput("error")

        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithTag("ErrorView").fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onNodeWithTag("ErrorView").assertIsDisplayed()
    }
}
