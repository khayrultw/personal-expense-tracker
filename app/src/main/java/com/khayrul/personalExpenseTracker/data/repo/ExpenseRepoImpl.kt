package com.khayrul.personalExpenseTracker.data.repo

import com.khayrul.personalExpenseTracker.data.db.ExpenseDao
import com.khayrul.personalExpenseTracker.data.model.Expense
import com.khayrul.personalExpenseTracker.data.model.ExpenseWithCategory
import kotlinx.coroutines.flow.Flow

class ExpenseRepoImpl(
    private val expenseDao: ExpenseDao
): ExpenseRepo {
    override fun getAllExpenses(): Flow<List<ExpenseWithCategory>> {
        return expenseDao.getAllExpenses()
    }

    override suspend fun getExpenseById(id: Int): ExpenseWithCategory? {
        return expenseDao.getExpenseById(id)
    }

    override suspend fun insertExpense(expense: Expense) {
        return expenseDao.insertExpense(expense)
    }

    override suspend fun deleteExpense(expense: Expense) {
        return expenseDao.deleteExpense(expense)
    }

    override suspend fun updateExpense(expense: Expense) {
        return expenseDao.updateExpense(expense)
    }

    override suspend fun updateCategoryForExpenses(oldCategoryId: Int, newCategoryId: Int) {
        return expenseDao.updateCategoryForExpenses(oldCategoryId, newCategoryId)
    }
}