package com.danlowe.poster.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danlowe.poster.R
import com.danlowe.poster.model.PostMessage
import com.danlowe.poster.ui.theme.PreviewPosterTheme
import java.util.Date

@Composable
fun NewPostBar(
  userName: String,
  modifier: Modifier = Modifier,
  submitPost: (PostMessage) -> Unit,
) {
  var newPost by rememberSaveable(stateSaver = TextFieldValue.Saver) {
    mutableStateOf(TextFieldValue())
  }

  OutlinedTextField(
    value = newPost,
    onValueChange = { newPost = it },
    modifier = modifier
      .fillMaxWidth()
      .testTag("newPostBar"),
    leadingIcon = {
      IconButton(
        onClick = {
          newPost = TextFieldValue()
        },
        modifier = Modifier.testTag("clearNewPost")
      ) {
        Icon(
          imageVector = Icons.Filled.Clear,
          contentDescription = stringResource(R.string.clear_search)
        )
      }
    },
    label = {
      Text(stringResource(R.string.submit_new_post))
    },
    placeholder = {
      Text(stringResource(R.string.submit_new_post))
    },
    trailingIcon = {
      IconButton(
        onClick = {
          submitPost(
            PostMessage(
              creator = userName,
              date = Date().time,
              message = newPost.text,
            )
          )
          newPost = TextFieldValue()
        },
        modifier = Modifier.testTag("submitNewPost")
      ) {
        Icon(
          imageVector = Icons.AutoMirrored.Filled.Send,
          contentDescription = stringResource(R.string.submit_new_post)
        )
      }
    }
  )
}

@Composable
@Preview
private fun PreviewNewPostBar() {
  PreviewPosterTheme {
    NewPostBar(
      userName = "Dan",
      submitPost = {},
      modifier = Modifier.padding(16.dp)
    )
  }
}