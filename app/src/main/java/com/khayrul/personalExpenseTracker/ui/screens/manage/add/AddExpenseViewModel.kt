package com.khayrul.personalExpenseTracker.ui.screens.manage.add

import androidx.lifecycle.viewModelScope
import com.khayrul.personalExpenseTracker.data.model.Expense
import com.khayrul.personalExpenseTracker.data.repo.CategoryRepo
import com.khayrul.personalExpenseTracker.data.repo.ExpenseRepo
import com.khayrul.personalExpenseTracker.ui.screens.manage.base.BaseManageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddExpenseViewModel(
    categoryRepo: CategoryRepo,
    private val expenseRepo: ExpenseRepo
): BaseManageViewModel(categoryRepo) {

    fun create() {
        viewModelScope.launch(Dispatchers.IO) {
            val state = uiState.value
            val expense = Expense(
                amount = state.amount,
                note = state.note,
                categoryId = state.category!!.id!!,
                date = state.date
            )
            expenseRepo.insertExpense(expense)
            _finish.emit(Unit)
        }
    }
}
