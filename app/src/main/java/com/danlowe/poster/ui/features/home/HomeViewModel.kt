package com.danlowe.poster.ui.features.home

import androidx.lifecycle.ViewModel
import com.danlowe.poster.model.PostMessage
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.Instant
import java.time.temporal.ChronoUnit

class HomeViewModel() : ViewModel() {

  private val _state = MutableStateFlow(
    PostsState(
      persistentListOf(
        PostMessage(
          creator = "Dan",
          message = "Hello everyone!",
          date = TEST_TIME.toEpochMilli()
        ),
        PostMessage(
          creator = "Dan",
          message = "How do you tell if the keyboard is showing on Android?",
          date = TEST_TIME.minus(20, ChronoUnit.HOURS).toEpochMilli()
        ),
        PostMessage(
          creator = "Dan",
          message = "I am a Material droid living in a Material world!",
          date = TEST_TIME.minus(41, ChronoUnit.HOURS).toEpochMilli()
        ),
        PostMessage(
          creator = "Dan",
          message = "Never",
          date = TEST_TIME.minus(62, ChronoUnit.HOURS).toEpochMilli()
        ),
        PostMessage(
          creator = "Dan",
          message = "gonna",
          date = TEST_TIME.minus(83, ChronoUnit.HOURS).toEpochMilli()
        ),
        PostMessage(
          creator = "Dan",
          message = "give",
          date = TEST_TIME.minus(104, ChronoUnit.HOURS).toEpochMilli()
        ),
        PostMessage(
          creator = "Dan",
          message = "you",
          date = TEST_TIME.minus(115, ChronoUnit.HOURS).toEpochMilli()
        ),
        PostMessage(
          creator = "Dan",
          message = "up",
          date = TEST_TIME.minus(126, ChronoUnit.HOURS).toEpochMilli()
        ),
      )
    )
  )
  val state: StateFlow<PostsState> get() = _state

  companion object {
    private val TEST_TIME = Instant.ofEpochMilli(1710021088)
  }

}
