package com.danlowe.poster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.danlowe.poster.ui.theme.PosterTheme
import com.danlowe.poster.ui.views.SimpleComposable

class MainActivity : ComponentActivity() {

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
          SimpleComposable("I'm a simple composable!")
        }
      }
    }
  }
}
