package com.danlowe.poster.repo.posts

import com.danlowe.poster.model.PostMessage
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MemoryPostRepoTest {
  private lateinit var ut: MemoryPostRepo

  @Before
  fun setUp() {
    ut = MemoryPostRepo()
  }

  @Test
  fun `submitPost should emit post`() = runTest {
    // Given
    val postMessage1 = PostMessage("Dan", 0, "Hello, world!1")
    val postMessage2 = PostMessage("Dan", 1, "Hello, world!2")

    // When
    ut.submitPost(postMessage1)
    ut.submitPost(postMessage2)

    // Then
    val result = ut.getPosts().firstOrNull()
    assertEquals(listOf(postMessage1, postMessage2), result)
  }
}