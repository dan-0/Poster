package com.danlowe.poster.ui.screens.login

import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_5
import app.cash.paparazzi.Paparazzi
import com.android.resources.NightMode
import com.danlowe.poster.util.SnapshotPosterTheme
import org.junit.Rule
import org.junit.Test

class LoginScreenSnapshotTest {
  @get:Rule
  val paparazzi = Paparazzi(
    deviceConfig = PIXEL_5,
  )

  @Test
  fun loginScreenDay() {
    loadLoginScreen()
  }

  @Test
  fun loginScreenDark() {
    paparazzi.unsafeUpdateConfig(PIXEL_5.copy(nightMode = NightMode.NIGHT))
    loadLoginScreen()
  }

  private fun loadLoginScreen() {
    paparazzi.snapshot {
      SnapshotPosterTheme {
        LoginScreen {}
      }
    }
  }
}