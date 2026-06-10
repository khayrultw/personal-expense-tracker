package com.khayrul.personalExpenseTracker.ui.screens.manage.add

import androidx.lifecycle.viewModelScope
import com.khayrul.personalExpenseTracker.core.ResourceProvider
import com.khayrul.personalExpenseTracker.data.repo.CategoryRepo
import com.khayrul.personalExpenseTracker.data.repo.ExpenseRepo
import com.khayrul.personalExpenseTracker.ui.screens.manage.base.BaseManageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddExpenseViewModel(
    resourceProvider: ResourceProvider,
    categoryRepo: CategoryRepo,
    private val expenseRepo: ExpenseRepo
): BaseManageViewModel(resourceProvider,categoryRepo) {

    fun create() {
        viewModelScope.launch(Dispatchers.IO) {
            errorHandler {
                validate()
                val expense = getExpense()
                expenseRepo.insertExpense(expense)
                _finish.emit(Unit)
            }

        }
    }
}
