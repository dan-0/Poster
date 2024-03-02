package com.danlowe.poster.ui.views

import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.danlowe.poster.R
import com.danlowe.poster.model.PostMessage
import com.danlowe.poster.ui.theme.PreviewPosterTheme
import com.danlowe.poster.utils.targetContext
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class NewPostBarTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  private val newPostBar get() = composeTestRule.onNodeWithTag("newPostBar")

  @Test
  fun newPostBarPlaceholderDisplayedOnEmpty() {
    composeTestRule.setContent {
      PreviewPosterTheme {
        NewPostBar(
          userName = USER_NAME
        ) {}
      }
    }

    newPostBar
      .assertIsDisplayed()
      .assertTextContains(targetContext.getString(R.string.submit_new_post))

    composeTestRule.onNodeWithTag("clearNewPost")
      .assertIsDisplayed()
      .assertContentDescriptionEquals(targetContext.getString(R.string.clear_search))

    composeTestRule.onNodeWithTag("submitNewPost")
      .assertIsDisplayed()
      .assertContentDescriptionEquals(targetContext.getString(R.string.submit_new_post))
  }

  @Test
  fun enteredTextIsDisplayed() {
    composeTestRule.setContent {
      PreviewPosterTheme {
        NewPostBar(
          userName = USER_NAME
        ) {}
      }
    }

    newPostBar.performTextInput("Hello, world!")
    newPostBar.assertTextContains("Hello, world!")
  }

  @Test
  fun enteredTextIsCleared() {
    composeTestRule.setContent {
      PreviewPosterTheme {
        NewPostBar(
          userName = USER_NAME
        ) {}
      }
    }

    newPostBar.performTextInput("Hello, world!")
    newPostBar.assertTextContains("Hello, world!")

    composeTestRule.onNodeWithTag("clearNewPost")
      .performClick()

    newPostBar.assertTextContains(targetContext.getString(R.string.submit_new_post))
  }

  @Test
  fun submitPostIsCalled() {
    var submittedPost: PostMessage? = null
    composeTestRule.setContent {
      PreviewPosterTheme {
        NewPostBar(
          userName = USER_NAME,
          submitPost = { post -> submittedPost = post }
        )
      }
    }

    newPostBar.performTextInput("Hello, world!")
    composeTestRule.onNodeWithTag("submitNewPost")
      .performClick()

    assertEquals(USER_NAME, submittedPost!!.creator)
    assertEquals("Hello, world!", submittedPost!!.message)
  }

  companion object {
    private const val USER_NAME = "Test User"
  }
}