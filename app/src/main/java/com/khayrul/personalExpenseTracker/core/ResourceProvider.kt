package com.khayrul.personalExpenseTracker.core

import android.content.Context

class ResourceProvider(
    private val context: Context
) {

    fun getString(resId: Int): String {
        return context.getString(resId)
    }

    fun getString(resId: Int, vararg args: Any): String {
        return context.getString(resId, *args)
    }
}