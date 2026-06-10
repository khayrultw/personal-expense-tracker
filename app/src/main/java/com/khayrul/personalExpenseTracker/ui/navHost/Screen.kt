package com.khayrul.personalExpenseTracker.ui.navHost

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    object Home : Screen()

    @Serializable
    object AddExpense : Screen()

    @Serializable
    data class EditExpense(val expenseId: Int) : Screen()
}