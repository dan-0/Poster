package com.danlowe.poster.ui.screens.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.danlowe.poster.R
import com.danlowe.poster.ui.theme.PreviewPosterTheme
import com.danlowe.poster.utils.targetContext
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun testLoginScreenDisplayedCorrectly() {
    composeTestRule.setContent {
      PreviewPosterTheme {
        LoginScreen {}
      }
    }

    composeTestRule.onNodeWithTag("loginScreen")
      .assertIsDisplayed()

    composeTestRule.onNodeWithTag("welcomeMessage")
      .assertIsDisplayed()
      .assertTextContains(targetContext.getString(R.string.welcome_message))

    composeTestRule.onNodeWithTag("usernameInput")
      .assertIsDisplayed()
      .assertTextContains(targetContext.getString(R.string.username))

    composeTestRule.onNodeWithTag("loginButton", useUnmergedTree = true)
      .assertIsDisplayed()
      .onChild()
      .assertTextContains(targetContext.getString(R.string.login))
  }

  @Test
  fun testLoginButtonClicked() {
    var username = ""
    composeTestRule.setContent {
      PreviewPosterTheme {
        LoginScreen { username = it }
      }
    }

    composeTestRule.onNodeWithTag("usernameInput")
      .performTextInput("testUser")

    composeTestRule.onNodeWithTag("loginButton")
      .performClick()

    assertEquals("testUser", username)
  }
}