package com.khayrul.personalExpenseTracker.data.repo

import com.khayrul.personalExpenseTracker.data.model.Expense
import com.khayrul.personalExpenseTracker.data.model.ExpenseWithCategory
import kotlinx.coroutines.flow.Flow

interface ExpenseRepo {
    fun getAllExpenses(): Flow<List<ExpenseWithCategory>>
    suspend fun getExpenseById(id: Int): ExpenseWithCategory?
    suspend fun insertExpense(expense: Expense)
    suspend fun deleteExpense(expense: Expense)
    suspend fun updateExpense(expense: Expense)
    suspend fun updateCategoryForExpenses(oldCategoryId: Int, newCategoryId: Int)
}