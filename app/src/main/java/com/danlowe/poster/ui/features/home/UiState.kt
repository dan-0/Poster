package com.danlowe.poster.ui.features.home

import com.danlowe.poster.model.PostMessage
import kotlinx.collections.immutable.ImmutableList

data class PostsState(
  val posts: ImmutableList<PostMessage>
)
