package com.danlowe.poster.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danlowe.poster.model.PostMessage
import com.danlowe.poster.model.UserName
import com.danlowe.poster.repo.login.LoginRepo
import com.danlowe.poster.repo.login.MemoryLoginRepo
import com.danlowe.poster.repo.posts.MemoryPostRepo
import com.danlowe.poster.repo.posts.PostsRepo
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class HomeViewModel(
  private val loginRepo: LoginRepo = MemoryLoginRepo(),
  private val postsRepo: PostsRepo = MemoryPostRepo()
) : ViewModel() {
  private val _state = MutableStateFlow<UiState>(UiState.Login)
  val state: StateFlow<UiState> get() = _state

  init {
    viewModelScope.launch(Dispatchers.IO) {
      loginRepo.getUserName().combine(postsRepo.getPosts()) { userName, posts ->
        UiState.Posts(userName, posts)
      }.collect {
        _state.value = it
      }
    }
  }

  fun signIn(userName: String) {
    loginRepo.signIn(UserName(userName))
  }
}

sealed class UiState {
  data object Login : UiState()
  data class Posts(
    val userName: String,
    val posts: ImmutableList<PostMessage>
  ) : UiState()
}