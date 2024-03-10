package com.danlowe.poster.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danlowe.poster.R
import com.danlowe.poster.ui.theme.PreviewPosterTheme
import kotlinx.coroutines.delay

@Composable
fun PostSearchBar(
  modifier: Modifier = Modifier,
  searchFilter: (String) -> Unit,
) {
  val searchQuery: MutableState<TextFieldValue> = rememberSaveable(
    stateSaver = TextFieldValue.Saver
  ) {
    mutableStateOf(TextFieldValue())
  }

  // Debounce the search query
  // We use [searchQuery] as a key, so that whenever it's changed, the launched effect restarts
  LaunchedEffect(key1 = searchQuery.value) {
    delay(500)
    searchFilter(searchQuery.value.text)
  }

  OutlinedTextField(
    value = searchQuery.value,
    onValueChange = { searchQuery.value = it },

    modifier = modifier
        .fillMaxWidth()
        .testTag("searchBar"),
    label = {
      Text(stringResource(R.string.search_posts))
    },
    leadingIcon = {
      IconButton(onClick = { searchQuery.value = TextFieldValue() }) {
        Icon(
          imageVector = Icons.Filled.Clear,
          contentDescription = stringResource(R.string.clear_search)
        )
      }
    }
  )
}

@Preview
@Composable
private fun PreviewPostSearchBar() {
  PreviewPosterTheme {
    PostSearchBar(searchFilter = {}, modifier = Modifier.padding(16.dp))
  }
}