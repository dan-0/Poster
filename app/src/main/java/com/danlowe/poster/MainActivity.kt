package com.danlowe.poster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.danlowe.poster.ui.features.home.HomeViewModel
import com.danlowe.poster.ui.features.home.PostsState
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
          val state: State<PostsState> = viewModel.state.collectAsState()

          PostsScreen(state = state.value)
        }
      }
    }
  }
}
