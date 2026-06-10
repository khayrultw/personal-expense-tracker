package com.khayrul.personalExpenseTracker.ui.screens.manage.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.khayrul.personalExpenseTracker.core.ResourceProvider
import com.khayrul.personalExpenseTracker.data.repo.CategoryRepo
import com.khayrul.personalExpenseTracker.data.repo.ExpenseRepo
import com.khayrul.personalExpenseTracker.ui.navHost.Screen
import com.khayrul.personalExpenseTracker.ui.screens.manage.base.BaseManageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditExpenseViewModel(
    savedStateHandle: SavedStateHandle,
    resourceProvider: ResourceProvider,
    categoryRepo: CategoryRepo,
    private val expenseRepo: ExpenseRepo
): BaseManageViewModel(resourceProvider, categoryRepo) {

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
                        title = item.expense.title,
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
            errorHandler {
                validate()
                val expense = getExpense(expenseId)
                expenseRepo.updateExpense(expense)
                _finish.emit(Unit)
            }
        }
    }
}
