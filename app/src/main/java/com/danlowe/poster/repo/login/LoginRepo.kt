package com.danlowe.poster.repo.login

import com.danlowe.poster.model.UserName
import kotlinx.coroutines.flow.Flow

interface LoginRepo {
  fun signIn(user: UserName)
  fun getUserName(): Flow<String>
}

