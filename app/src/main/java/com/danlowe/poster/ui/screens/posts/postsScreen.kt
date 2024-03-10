package com.danlowe.poster.ui.screens.posts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danlowe.poster.model.PostMessage
import com.danlowe.poster.ui.features.home.PostsState
import com.danlowe.poster.ui.theme.PreviewPosterTheme
import com.danlowe.poster.ui.views.PostItem
import com.danlowe.poster.ui.views.PostSearchBar
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun PostsScreen(state: PostsState) {
  var searchQuery: String by remember {
    mutableStateOf("")
  }

  val filteredSearches: ImmutableList<PostMessage> by remember(searchQuery, state.posts) {
    mutableStateOf(
      if (searchQuery.isEmpty()) {
        state.posts
      } else {
        state.posts.filter { post ->
          post.message.contains(searchQuery, ignoreCase = true)
        }.toImmutableList()
      }
    )
  }

  LazyColumn(
    modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
        .padding(bottom = 16.dp)
        .testTag("postsList"),
    // Provide a default vertical spacing for the column
    verticalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    // We add views to a lazy column with the `item` API
    // We provide a key so if position changes with a state change, compose can still remember
    // the state
    // Items are added in order of display
    item("postSearchBar") {
      PostSearchBar { newQuery ->
        searchQuery = newQuery
      }
    }

    // This uses one of the `items` APIs to populate items in a list
    // Note the key uses a lambda to create a unique key for each item
    items(items = filteredSearches, key = { "post${it.date}{${it.creator}" }) { post ->
      PostItem(post)
    }
  }
}

@Preview
@Composable
private fun PreviewPostScreen() {
  PreviewPosterTheme {
    PostsScreen(
      state = PostsState(
        posts = listOf(
          PostMessage(
            creator = "Dan",
            date = 0,
            message = "Hello, world!"
          )
        ).toImmutableList()
      )
    )
  }
}





