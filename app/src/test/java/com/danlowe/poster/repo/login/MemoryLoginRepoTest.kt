package com.danlowe.poster.repo.login

import com.danlowe.poster.model.UserName
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MemoryLoginRepoTest {
  private lateinit var ut: MemoryLoginRepo

  @Before
  fun setUp() {
    ut = MemoryLoginRepo()
  }

  @Test
  fun `signIn should emit user name`() = runTest {
    // Given
    val userName = UserName("Dan")

    // When
    ut.signIn(userName)

    // Then
    val result = ut.getUserName().firstOrNull()
    assertEquals("Dan", result)
  }

  @Test
  fun `multiple signIns emit last`() = runTest {
    // Given
    val userName1 = UserName("Dan")
    val userName2 = UserName("John")

    // When
    ut.signIn(userName1)
    ut.signIn(userName2)

    // Then
    val result = ut.getUserName().firstOrNull()
    assertEquals("John", result)
  }
}