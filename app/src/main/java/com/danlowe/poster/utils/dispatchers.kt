package com.danlowe.poster.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface AppDispatchers {
  val main: CoroutineDispatcher
  val io: CoroutineDispatcher
  val default: CoroutineDispatcher
  val unconfined: CoroutineDispatcher
}

class RealAppDispatchers : AppDispatchers {
  override val main: CoroutineDispatcher
    get() = Dispatchers.Main
  override val io: CoroutineDispatcher
    get() = Dispatchers.IO
  override val default: CoroutineDispatcher
    get() = Dispatchers.Default
  override val unconfined: CoroutineDispatcher
    get() = Dispatchers.Unconfined
}