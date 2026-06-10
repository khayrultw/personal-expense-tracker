package com.khayrul.personalExpenseTracker.ui.screens.home.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.khayrul.personalExpenseTracker.ui.navHost.Screen
import com.khayrul.personalExpenseTracker.ui.screens.home.HomeUiState

@Composable
fun HistoryContent(
    uiState: HomeUiState,
    navController: NavController,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        if (uiState.expenses.isEmpty()) {
            Box(Modifier.weight(1f)) {
                EmptyState()
            }
        } else {
            ExpenseList(
                expenses = uiState.expenses,
                onItemClick = { item ->
                    navController.navigate(Screen.EditExpense(item.expense.id!!))
                }
            )
        }
    }
}