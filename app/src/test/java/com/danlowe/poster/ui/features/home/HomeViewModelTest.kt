package com.danlowe.poster.ui.features.home

import com.danlowe.poster.model.PostMessage
import com.danlowe.poster.util.TestDispatchers
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {
  private lateinit var ut: HomeViewModel
  @OptIn(ExperimentalCoroutinesApi::class)
  private val testDispatcher = UnconfinedTestDispatcher()

  @Before
  fun setUp() {
    ut = HomeViewModel(
      dispatchers = TestDispatchers(testDispatcher)
    )
  }

  @Test
  fun initialStateIsLogin() = runTest {
    // Given
    val expected = UiState.Login

    // When
    val result = ut.state.value

    // Then
    assertEquals(expected, result)
  }

  @Test
  fun `signIn changes state to Posts`() = runTest {
    // Given
    val expected = UiState.Posts(TEST_USER_NAME, persistentListOf())

    // When
    signIn()

    // Then
    val result = ut.state.value
    assertEquals(expected, result)
  }

  @Test
  fun `submitPost should emit post with user name`() = runTest {
    // Given
    signIn()
    val postMessage1 = PostMessage(TEST_USER_NAME, 0, "Hello, world!1")
    val postMessage2 = PostMessage(TEST_USER_NAME, 1, "Hello, world!2")

    val expected: UiState.Posts = UiState.Posts(
      TEST_USER_NAME,
      persistentListOf(postMessage1, postMessage2)
    )

    // When
    ut.submitPost(postMessage1)
    ut.submitPost(postMessage2)

    // Then
    val result = ut.state.value
    assertEquals(expected, result)
  }

  private fun signIn() {
    ut.signIn(TEST_USER_NAME)
  }

  companion object {
    private const val TEST_USER_NAME = "Dan"
  }
}