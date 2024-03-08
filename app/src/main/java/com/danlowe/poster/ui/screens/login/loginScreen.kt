package com.danlowe.poster.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danlowe.poster.R
import com.danlowe.poster.ui.theme.PreviewPosterTheme

/**
 * A simple login screen with a username input and a login button. On button click, the username is
 * saved.
 */
@Composable
fun LoginScreen(login: (String) -> Unit) {
  // Use the outer column to center and space the contents
  Column(
    modifier = Modifier
        .fillMaxSize()
        // Note, we're using dp directly, you may want a dimens object for this
        .padding(16.dp)
//      .border(1.dp, Color.Blue) // <- Order matters!
        .testTag("loginScreen"),
    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Use TextFieldValue over string so we can maintain cursor position, don't forget stateSaver!
    // Use rememberSaveable to save state in the event of configuration change or process death
    var userName by rememberSaveable(stateSaver = TextFieldValue.Saver) {
      mutableStateOf(TextFieldValue())
    }

    // A simple welcome message
    Text(
      text = stringResource(R.string.welcome_message),
      modifier = Modifier.testTag("welcomeMessage")
    )

    // User input field with a label
    OutlinedTextField(
      value = userName,
      onValueChange = { userName = it },
      modifier = Modifier
          .fillMaxWidth()
          .testTag("usernameInput"),
      label = {
        Text(stringResource(R.string.username))
      }
    )

    // Space out the layout using weight so the CTA is pushed to the bottom
    Spacer(modifier = Modifier.weight(1f))

    // We want to have the button at the end of the layout, so wrap in a Row with Arrangement.End
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.End
    ) {
      // Simple button with click behavior and text
      Button(
        onClick = { login(userName.text) },
        modifier = Modifier.testTag("loginButton")
      ) {
        Text(stringResource(R.string.login))
      }
    }
  }
}

@Preview
@Composable
fun PreviewLoginScreen() {
  PreviewPosterTheme {
    LoginScreen(login = {})
  }
}