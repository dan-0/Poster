package com.danlowe.poster.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Send
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
  submitPost: (PostMessage) -> Unit,
  modifier: Modifier = Modifier,
) {
  var newPost by rememberSaveable(stateSaver = TextFieldValue.Saver) {
    mutableStateOf(TextFieldValue())
  }

  OutlinedTextField(
    value = newPost,
    onValueChange = { newPost = it },
    modifier = modifier.fillMaxWidth(),
    leadingIcon = {
      IconButton(
        onClick = {
          newPost = TextFieldValue()
        }
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
        }
      ) {
        Icon(
          imageVector = Icons.Filled.Send,
          contentDescription = stringResource(R.string.clear_search)
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