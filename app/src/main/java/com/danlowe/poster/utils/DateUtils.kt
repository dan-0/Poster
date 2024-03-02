package com.danlowe.poster.utils

import android.content.Context
import android.text.format.DateUtils
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun Long.toDateString(): String {
  val context = LocalContext.current
  return DateUtils.formatDateTime(context, this, DEFAULT_FLAGS)
}

fun Long.toDateString(context: Context): String {
  return DateUtils.formatDateTime(context, this, DEFAULT_FLAGS)
}

private const val DEFAULT_FLAGS = DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_ABBREV_MONTH or
    DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_SHOW_TIME