package com.danlowe.poster.repo.posts

import com.danlowe.poster.model.PostMessage
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MemoryPostRepo : PostsRepo {
  private val posts = MutableStateFlow<ImmutableList<PostMessage>>(persistentListOf())

  override fun submitPost(postMessage: PostMessage) {
    posts.value = posts.value.toMutableList().apply {
      add(postMessage)
    }.toImmutableList()
  }

  override fun getPosts(): Flow<ImmutableList<PostMessage>> = posts
}