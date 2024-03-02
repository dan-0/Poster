package com.danlowe.poster.ui.views

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.danlowe.poster.R
import com.danlowe.poster.ui.theme.PreviewPosterTheme
import com.danlowe.poster.utils.targetContext
import org.junit.Rule
import org.junit.Test

class PostHeaderTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun postHeaderDisplaysCorrectly() {
    composeTestRule.setContent {
      PreviewPosterTheme {
        PostHeader(
          userName = "Alice"
        )
      }
    }

    composeTestRule
      .onNodeWithTag("postHeader")
      .assertTextEquals(targetContext.getString(R.string.users_posts, "Alice"))
  }

}