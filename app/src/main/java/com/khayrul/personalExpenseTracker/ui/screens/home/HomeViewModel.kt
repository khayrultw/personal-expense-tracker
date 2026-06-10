package com.khayrul.personalExpenseTracker.ui.screens.home

import androidx.lifecycle.viewModelScope
import com.khayrul.personalExpenseTracker.data.model.Category
import com.khayrul.personalExpenseTracker.data.model.ExpenseWithCategory
import com.khayrul.personalExpenseTracker.data.repo.CategoryRepo
import com.khayrul.personalExpenseTracker.data.repo.ExpenseRepo
import com.khayrul.personalExpenseTracker.ui.base.BaseViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import java.util.Calendar

class HomeViewModel(
    expenseRepo: ExpenseRepo,
    categoryRepo: CategoryRepo
): BaseViewModel() {

    val uiState = combine(
        expenseRepo.getAllExpenses(),
        categoryRepo.getAllCategories(),
    ) { expenses, categories ->
        val totalCost = expenses.sumOf { it.expense.amount }

        val categoryCosts = expenses.groupBy { it.category }
            .map { (category, expenseList) ->
                CategoryCost(
                    categoryName = category.name,
                    color = category.color,
                    amount = expenseList.sumOf { it.expense.amount }
                )
            }.sortedByDescending { it.amount }

        val lastMonthCost = calcLastMonthCost(expenses)

        HomeUiState(
            expenses = expenses,
            totalCost = totalCost,
            currentMonthCost = lastMonthCost,
            categoryCosts = categoryCosts,
            categories = categories
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        HomeUiState()
    )

    fun calcLastMonthCost(expenses: List<ExpenseWithCategory>): Double {
        val calendar = Calendar.getInstance()
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentMonthYear = calendar.get(Calendar.YEAR)

        val currentMonthCost = expenses.filter {
            val expenseCal = Calendar.getInstance()
            expenseCal.timeInMillis = it.expense.date
            expenseCal.get(Calendar.MONTH) == currentMonth && expenseCal.get(Calendar.YEAR) == currentMonthYear
        }.sumOf { it.expense.amount }

        return currentMonthCost
    }
}

data class HomeUiState(
    val expenses: List<ExpenseWithCategory> = emptyList(),
    val totalCost: Double = 0.0,
    val currentMonthCost: Double = 0.0,
    val categoryCosts: List<CategoryCost> = emptyList(),
    val categories: List<Category> = emptyList(),
)

data class CategoryCost(
    val categoryName: String,
    val color: Int,
    val amount: Double
)
