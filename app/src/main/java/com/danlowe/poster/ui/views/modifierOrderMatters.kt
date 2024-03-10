package com.danlowe.poster.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danlowe.poster.ui.theme.PreviewPosterTheme

@Composable
fun ModifierOrderMatters() {
  Column(
    modifier = Modifier.border(1.dp, color = Color.Green)
  ) {
    Box(
      modifier = Modifier
          .background(color = Color.Blue)
          .size(100.dp)
    )
    Box(
      modifier = Modifier
          .size(100.dp)
          .background(color = Color.Yellow)
    )
    // we want 20 padding around the box -> box should be 100dp, background is red
    Box(
      modifier = Modifier
          .padding(20.dp)
          .size(100.dp)
          .background(color = Color.Red)
          .border(1.dp, color = Color.White)
    )

    // Box size is 100dp, we want 20dp padding within the box, background is red
    Box(
      modifier = Modifier
          .size(100.dp)
          .padding(20.dp)
          .background(color = Color.Red)
          .border(1.dp, color = Color.White)
    )
    //
    Box(
      modifier = Modifier
          .size(100.dp)
          .background(color = Color.Red)
          .padding(20.dp)
          .border(1.dp, color = Color.White)
    )
    Box(
      modifier = Modifier
          .background(color = Color.Red)
          .padding(20.dp)
          .size(100.dp)
          .border(1.dp, color = Color.White)
    )
  }
}

@Preview
@Composable
fun PreviewModifierOrderMatters() {
  PreviewPosterTheme {
    ModifierOrderMatters()
  }
}