package com.khayrul.personalExpenseTracker.ui.screens.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khayrul.personalExpenseTracker.data.model.ExpenseWithCategory

@Composable
fun ExpenseList(
    expenses: List<ExpenseWithCategory>,
    onItemClick: (ExpenseWithCategory) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 64.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(expenses) { expense ->
            ExpenseItem(
                expense = expense,
                onClick = { onItemClick(expense) }
            )
        }
    }
}