package com.khayrul.personalExpenseTracker.core.utils

fun String.truncate(max: Int): String {
    return if (length > max) take(max - 1) + "…" else this
}