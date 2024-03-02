package com.danlowe.poster.repo.posts

import com.danlowe.poster.model.PostMessage
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

interface PostsRepo {
  fun submitPost(postMessage: PostMessage)
  fun getPosts(): Flow<ImmutableList<PostMessage>>
}

