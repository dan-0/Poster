package com.danlowe.poster.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import com.danlowe.poster.ui.theme.PosterTheme

@Composable
fun SnapshotPosterTheme(content: @Composable () -> Unit) {
  CompositionLocalProvider(LocalInspectionMode provides true) {
    PosterTheme {
      Surface(
        modifier = Modifier
          .fillMaxWidth()
          .wrapContentHeight(),
        color = MaterialTheme.colorScheme.background
      ) {
        content()
      }
    }
  }
}