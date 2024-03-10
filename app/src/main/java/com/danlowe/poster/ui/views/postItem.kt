package com.danlowe.poster.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danlowe.poster.R
import com.danlowe.poster.model.PostMessage
import com.danlowe.poster.ui.theme.PreviewPosterTheme
import com.danlowe.poster.utils.toDateString

@Composable
fun PostItem(
  postMessage: PostMessage,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .wrapContentHeight()
//      .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
      .testTag("postItem${postMessage.hashCode()}"),
    shape = RoundedCornerShape(8.dp),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    ) {
      Text(
        text = postMessage.message,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.testTag("postMessage"),
      )
      Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,
      ) {
        Text(
          text = postMessage.date.toDateString(),
          style = MaterialTheme.typography.bodySmall,
          modifier = Modifier.testTag("postDate"),
        )
        Text(
          text = stringResource(R.string.post_username, postMessage.creator),
          style = MaterialTheme.typography.bodySmall,
          modifier = Modifier.testTag("postCreator")
        )
      }
    }
  }
}

@Preview
@Composable
private fun PreviewPostItem() {
  PreviewPosterTheme {
    PostItem(
      PostMessage(
        creator = "Dan",
        date = 1620000000000,
        message = "Hello, world!",
      ),
      Modifier.padding(16.dp),
    )
  }
}