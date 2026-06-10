package com.khayrul.personalExpenseTracker.ui.screens.manage.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.khayrul.personalExpenseTracker.data.model.Expense
import com.khayrul.personalExpenseTracker.data.repo.CategoryRepo
import com.khayrul.personalExpenseTracker.data.repo.ExpenseRepo
import com.khayrul.personalExpenseTracker.ui.navHost.Screen
import com.khayrul.personalExpenseTracker.ui.screens.manage.base.BaseManageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditExpenseViewModel(
    savedStateHandle: SavedStateHandle,
    categoryRepo: CategoryRepo,
    private val expenseRepo: ExpenseRepo
): BaseManageViewModel(categoryRepo) {

    private val expenseId = savedStateHandle.toRoute<Screen.EditExpense>().expenseId
    init {
        loadExpense()
    }

    private fun loadExpense() {
        viewModelScope.launch {
            val expenseWithCategory = expenseRepo.getExpenseById(expenseId)
            expenseWithCategory?.let { item ->
                _uiState.update { 
                    it.copy(
                        amount = item.expense.amount,
                        note = item.expense.note,
                        category = item.category,
                        date = item.expense.date
                    )
                }
            }
        }
    }

    fun update() {
        viewModelScope.launch(Dispatchers.IO) {
            val state = uiState.value
            val expense = Expense(
                id = expenseId,
                amount = state.amount,
                note = state.note,
                categoryId = state.category!!.id!!,
                date = state.date
            )
            expenseRepo.updateExpense(expense)
            _finish.emit(Unit)
        }
    }
}
