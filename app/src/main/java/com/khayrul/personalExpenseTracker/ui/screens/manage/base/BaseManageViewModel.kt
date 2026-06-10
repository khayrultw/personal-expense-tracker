package com.khayrul.personalExpenseTracker.ui.screens.manage.base

import androidx.lifecycle.viewModelScope
import com.khayrul.personalExpenseTracker.R
import com.khayrul.personalExpenseTracker.core.ResourceProvider
import com.khayrul.personalExpenseTracker.data.model.Category
import com.khayrul.personalExpenseTracker.data.model.Expense
import com.khayrul.personalExpenseTracker.data.repo.CategoryRepo
import com.khayrul.personalExpenseTracker.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseManageViewModel(
    private val resourceProvider: ResourceProvider,
    private val categoryRepo: CategoryRepo
) : BaseViewModel() {
    protected val _uiState = MutableStateFlow(ManageUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            categoryRepo.getAllCategories().collect { categories ->
                _uiState.update { it.copy(categories = categories) }
            }
        }
    }

    fun onAmountChange(amount: Double) {
        _uiState.update { it.copy(amount = amount) }
    }

    fun onTitleChange(title: String) {
        _uiState.update { it.copy(title = title) }
    }

    fun onNoteChange(note: String) {
        _uiState.update { it.copy(note = note) }
    }

    fun onCategoryChange(category: Category) {
        _uiState.update { it.copy(category = category) }
    }

    fun onDateChange(date: Long) {
        _uiState.update { it.copy(date = date) }
    }

    protected fun validate() {
        val state = uiState.value
        require(state.title.isNotBlank()) {
            resourceProvider.getString(R.string.title_required)
        }
        require(state.amount > 0) {
            resourceProvider.getString(R.string.amount_greater_than_zero)
        }
        require (state.category != null) {
            resourceProvider.getString(R.string.category_required)
        }
        require(state.date <= System.currentTimeMillis()) {
            resourceProvider.getString(R.string.date_in_future)
        }
    }

    protected fun getExpense(id: Int? = null): Expense {
        val state = uiState.value
        return Expense(
            id = id,
            title = state.title,
            amount = state.amount,
            note = state.note,
            categoryId = state.category!!.id!!,
            date = state.date
        )
    }
}

data class ManageUiState(
    val title: String = "",
    val amount: Double = 0.0,
    val note: String = "",
    val category: Category? = null,
    val date: Long = System.currentTimeMillis(),
    val categories: List<Category> = emptyList()
)
