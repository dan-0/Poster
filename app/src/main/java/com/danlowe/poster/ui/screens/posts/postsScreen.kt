package com.danlowe.poster.ui.screens.posts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danlowe.poster.model.PostMessage
import com.danlowe.poster.ui.features.home.UiState
import com.danlowe.poster.ui.theme.PreviewPosterTheme
import com.danlowe.poster.ui.views.NewPostBar
import com.danlowe.poster.ui.views.PostHeader
import com.danlowe.poster.ui.views.PostItem
import com.danlowe.poster.ui.views.PostSearchBar
import kotlinx.collections.immutable.toImmutableList

@Composable
fun PostsScreen(state: UiState.Posts, submitPost: (PostMessage) -> Unit) {

  // Note, there is a bug here, because we don't use rememberSaveable, this is lost on
  //  configuration change, but
  var searchQuery by remember {
    mutableStateOf("")
  }

  val filteredSearches by remember(searchQuery, state.posts) {
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

  // We want a Column to fill the full screen so we can place scrollable and non-scrollable content
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
  ) {

    // We use a LazyColumn when we want a memory efficient list of items like a RecyclerView
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        // This is the main view in the column, so we want it to fill max weight
        .weight(1f),
      // Provide a default vertical spacing for the column
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      // We add views to a lazy column with the `item` API
      // We provide a key so if position changes with a state change, compose can still remember
      // the state
      // Items are added in order of display
      item("postHeader") {
        PostHeader(state.userName)
      }

      item("postSearchBar") {
        PostSearchBar { newQuery ->
          searchQuery = newQuery
        }
      }

      // This uses one of the `items` APIs to populate items in a list
      // Note the key uses a lambda to create a unique key for each item
      items(items = filteredSearches, key =  { "post${it.date}{${it.creator}" }) { post ->
        PostItem(post)
      }
    }

    // This sits at the bottom of the parent column
    // Note we're passing in a modifier so we have spacing,
    NewPostBar(state.userName, submitPost, modifier = Modifier.padding(top = 8.dp))
  }
}

@Preview
@Composable
private fun PreviewPostScreen() {
  PreviewPosterTheme {
    PostsScreen(
      state = UiState.Posts(
        userName = "Dan",
        posts = listOf(
          PostMessage(
            creator = "Dan",
            date = 0,
            message = "Hello, world!"
          )
        ).toImmutableList()
      )
    ) {}
  }
}





