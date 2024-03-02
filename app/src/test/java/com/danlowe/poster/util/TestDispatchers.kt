package com.danlowe.poster.util

import com.danlowe.poster.utils.AppDispatchers
import kotlinx.coroutines.CoroutineDispatcher

class TestDispatchers(testDispatcher: CoroutineDispatcher) : AppDispatchers {
  override val main: CoroutineDispatcher = testDispatcher
  override val io: CoroutineDispatcher = testDispatcher
  override val default: CoroutineDispatcher = testDispatcher
  override val unconfined: CoroutineDispatcher = testDispatcher
}