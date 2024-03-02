package com.danlowe.poster.ui.screens.posts

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import com.danlowe.poster.model.PostMessage
import com.danlowe.poster.ui.features.home.UiState
import com.danlowe.poster.ui.theme.PreviewPosterTheme
import kotlinx.collections.immutable.persistentListOf
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PostsScreenTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun postsScreenDisplayedCorrectly() {
    composeTestRule.setContent {
      PreviewPosterTheme {
        PostsScreen(TEST_STATE) {}
      }
    }
    val postsList = composeTestRule.onNodeWithTag("postsList")

    postsList.assertIsDisplayed()

    postsList.performScrollToIndex(0)
    composeTestRule.onNodeWithTag("postHeader").assertIsDisplayed()

    postsList.performScrollToIndex(1)
    composeTestRule.onNodeWithTag("searchBar").assertIsDisplayed()

    postsList.performScrollToIndex(2)
    composeTestRule.onNodeWithTag("postItem${POST1.hashCode()}").assertIsDisplayed()

    postsList.performScrollToIndex(3)
    composeTestRule.onNodeWithTag("postItem${POST2.hashCode()}").assertIsDisplayed()

    composeTestRule.onNodeWithTag("newPostBar").assertIsDisplayed()
  }

  @Test
  fun postsScreenSearchFilter() {
    composeTestRule.mainClock.autoAdvance = false
    composeTestRule.setContent {
      PreviewPosterTheme {
        PostsScreen(TEST_STATE) {}
      }
    }

    composeTestRule.onNodeWithTag("searchBar")
      .performTextInput("!1")

    // sanity check item exists before advancing the clock
    composeTestRule.onNodeWithTag("postItem${POST2.hashCode()}").assertIsDisplayed()

    // filter is debounced at 500ms currently
    composeTestRule.mainClock.advanceTimeBy(550)

    composeTestRule.onNodeWithTag("postItem${POST1.hashCode()}").assertIsDisplayed()
    composeTestRule.onNodeWithTag("postItem${POST2.hashCode()}").assertDoesNotExist()
  }

  @Test
  fun postsScreenAddNewPost() {
    var newPost: PostMessage? = null
    composeTestRule.setContent {
      PreviewPosterTheme {
        PostsScreen(TEST_STATE) {
          newPost = it
        }
      }
    }

    composeTestRule.onNodeWithTag("newPostBar").performTextInput("Hello, world!3")
    composeTestRule.onNodeWithTag("submitNewPost").performClick()

    assertEquals("Hello, world!3", newPost!!.message)
  }

  companion object {

    private val POST1 = PostMessage(
      date = 1,
      message = "Hello, world!1",
      creator = "Dan"
    )
    private val POST2 = PostMessage(
      date = 2,
      message = "Hello, world!2",
      creator = "Dan"
    )

    private val TEST_STATE = UiState.Posts(
      userName = "TEST_USER",
      posts = persistentListOf(
        POST1,
        POST2
      )
    )
  }
}