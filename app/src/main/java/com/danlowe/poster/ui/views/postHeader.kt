package com.danlowe.poster.ui.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danlowe.poster.R
import com.danlowe.poster.ui.theme.PreviewPosterTheme

@Composable
fun PostHeader(userName: String, modifier: Modifier = Modifier) {
  Text(
    text = stringResource(R.string.users_posts, userName),
    style = MaterialTheme.typography.headlineLarge,
    modifier = modifier.testTag("postHeader")
  )
}

@Preview
@Composable
private fun PreviewPostHeader() {
  PreviewPosterTheme {
    PostHeader(userName = "Dan Lowe", modifier = Modifier.padding(16.dp))
  }
}