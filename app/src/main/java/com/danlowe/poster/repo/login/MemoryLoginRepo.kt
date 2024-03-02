package com.danlowe.poster.repo.login

import com.danlowe.poster.model.UserName
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

class MemoryLoginRepo : LoginRepo {

  private val userState = MutableSharedFlow<UserName>(1, 0, BufferOverflow.DROP_OLDEST)

  override fun signIn(user: UserName) {
    userState.tryEmit(user)
  }

  override fun getUserName(): Flow<String> = userState.map { it.name }
}