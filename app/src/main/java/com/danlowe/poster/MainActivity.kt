package com.danlowe.poster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.danlowe.poster.ui.features.home.HomeViewModel
import com.danlowe.poster.ui.features.home.UiState
import com.danlowe.poster.ui.screens.login.LoginScreen
import com.danlowe.poster.ui.screens.posts.PostsScreen
import com.danlowe.poster.ui.theme.PosterTheme

class MainActivity : ComponentActivity() {

  private val viewModel: HomeViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // Notes:
    // There is a similar concept for Fragments
    // setContent is essentially an extension that creates a ComposeView and sets the content to
    //  that view. Your composable content is displayed within the ComposeView
    setContent {
      PosterTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          val state = viewModel.state.collectAsState()

          // Note: Compose has a number of different navigation libraries, for the purpose of this
          // talk we are using a simple state based navigation
          when (val currentState = state.value) {
            is UiState.Login -> LoginScreen { userName ->
              viewModel.signIn(userName)
            }
            is UiState.Posts -> PostsScreen(state = currentState) { postMessage ->
              viewModel.submitPost(postMessage)
            }
          }
        }
      }
    }
  }
}
