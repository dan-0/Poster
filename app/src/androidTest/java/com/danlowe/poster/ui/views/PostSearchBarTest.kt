package com.danlowe.poster.ui.views

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import com.danlowe.poster.R
import com.danlowe.poster.ui.theme.PreviewPosterTheme
import com.danlowe.poster.utils.targetContext
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PostSearchBarTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun testSearchBarDisplayedCorrectly() {
    composeTestRule.setContent {
      PreviewPosterTheme {
        PostSearchBar {

        }
      }
    }

    composeTestRule.onNodeWithTag("searchBar")
      .assertIsDisplayed()
      .assertTextContains(targetContext.getString(R.string.search_posts))
  }

  @Test
  fun searchTextIsEmitted() {
    var searchText = ""

    // We want to control the clock because searches are debounced
    composeTestRule.mainClock.autoAdvance = false

    composeTestRule.setContent {
      PreviewPosterTheme {
        PostSearchBar { text ->
          searchText = text
        }
      }
    }

    val searchBar = composeTestRule.onNodeWithTag("searchBar")

    searchBar.performTextInput("Hello")

    // We're debouncing, so there should be nothing until the debounce time is hit
    assertEquals("", searchText)

    // advance time by just a little longer than the debouncer is set
    composeTestRule.mainClock.advanceTimeBy(550)

    assertEquals("Hello", searchText)
  }
}