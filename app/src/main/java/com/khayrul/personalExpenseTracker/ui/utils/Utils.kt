package com.khayrul.personalExpenseTracker.ui.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


fun millisToDateString(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = millis
    return formatter.format(calendar.time)
}

fun String.truncate(max: Int): String {
    return if (length > max) take(max - 1) + "…" else this
}