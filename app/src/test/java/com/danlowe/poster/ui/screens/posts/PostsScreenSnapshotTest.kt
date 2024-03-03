package com.danlowe.poster.ui.screens.posts

import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_5
import app.cash.paparazzi.Paparazzi
import com.android.resources.NightMode
import com.danlowe.poster.model.PostMessage
import com.danlowe.poster.ui.features.home.UiState
import com.danlowe.poster.util.SnapshotPosterTheme
import kotlinx.collections.immutable.persistentListOf
import org.junit.Rule
import org.junit.Test

class PostsScreenSnapshotTest {
  @get:Rule
  val paparazzi = Paparazzi(
    deviceConfig = PIXEL_5,
  )

  @Test
  fun loadPostsScreenDay() {
    loadPostsScreen()
  }

  @Test
  fun loadPostsScreenDark() {
    paparazzi.unsafeUpdateConfig(PIXEL_5.copy(nightMode = NightMode.NIGHT))
    loadPostsScreen()
  }

  private fun loadPostsScreen() {
    paparazzi.snapshot {
      SnapshotPosterTheme {
        PostsScreen(TEST_STATE) {}
      }
    }
  }

  companion object {
    private val TEST_STATE = UiState.Posts(
      posts = persistentListOf(
        PostMessage(
          creator = "Creator 1",
          message = "Message 1",
          date = 1
        ),
        PostMessage(
          creator = "Creator 2",
          message = "Message 2",
          date = 2
        )
      ),
      userName = "Alice"
    )
  }
}