package com.danlowe.poster.ui.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danlowe.poster.ui.theme.PreviewPosterTheme

@Composable
fun SimpleComposable(title: String) {
  Column(modifier = Modifier.fillMaxSize()) {

    Text(text = title)

    Row(
      modifier = Modifier
          .padding(16.dp)
          .border(1.dp, Color.Red)
          .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

      val isChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }

      SimpleCheckBox(isChecked.value) { newIsCheckedState ->
        isChecked.value = newIsCheckedState
      }

      SimpleText(isChecked.value)
    }
  }
}

@Composable
fun SimpleIcon() {
  Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Add")
}

@Composable
fun SimpleCheckBox(
  isChecked: Boolean,
  onCheckedChange: (Boolean) -> Unit
) {
  Checkbox(checked = isChecked, onCheckedChange = onCheckedChange)
}

@Composable
fun SimpleText(isChecked: Boolean) {
  val text = if (isChecked) "Thank you for checking!" else "Please check me!"
  Text(text)
}

@Preview
@Composable
private fun PreviewSimpleComposable() {
  PreviewPosterTheme {
    SimpleComposable(title = "I'm a Simple Composable")
  }
}