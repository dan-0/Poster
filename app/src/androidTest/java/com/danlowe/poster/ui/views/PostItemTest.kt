package com.danlowe.poster.ui.views

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.danlowe.poster.R
import com.danlowe.poster.model.PostMessage
import com.danlowe.poster.ui.theme.PreviewPosterTheme
import com.danlowe.poster.utils.targetContext
import com.danlowe.poster.utils.toDateString
import org.junit.Rule
import org.junit.Test

class PostItemTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun postItemDisplaysCorrectly() {
    val post = PostMessage(
      creator = "Alice",
      message = "Hello, world!",
      date = 1620000000000,
    )
    composeTestRule.setContent {
      PreviewPosterTheme {
        PostItem(post)
      }
    }

    composeTestRule.onNodeWithTag("postItem${post.hashCode()}")
      .assertIsDisplayed()

    composeTestRule.onNodeWithTag("postMessage")
      .assertIsDisplayed()
      .assertTextEquals("Hello, world!")

    composeTestRule.onNodeWithTag("postDate")
      .assertIsDisplayed()
      .assertTextEquals(post.date.toDateString(targetContext))

    composeTestRule.onNodeWithTag("postCreator")
      .assertIsDisplayed()
      .assertTextEquals(targetContext.getString(R.string.post_username, post.creator))
  }
}