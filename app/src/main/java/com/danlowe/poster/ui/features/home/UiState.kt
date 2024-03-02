package com.danlowe.poster.ui.features.home

import com.danlowe.poster.model.PostMessage
import kotlinx.collections.immutable.ImmutableList

sealed class UiState {
  data object Login : UiState()
  data class Posts(
    val userName: String,
    val posts: ImmutableList<PostMessage>
  ) : UiState()
}