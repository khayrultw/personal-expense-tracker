package com.khayrul.personalExpenseTracker.ui.screens.manage.base

import androidx.lifecycle.viewModelScope
import com.khayrul.personalExpenseTracker.data.model.Category
import com.khayrul.personalExpenseTracker.data.repo.CategoryRepo
import com.khayrul.personalExpenseTracker.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseManageViewModel(
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

    fun onNoteChange(note: String) {
        _uiState.update { it.copy(note = note) }
    }

    fun onCategoryChange(category: Category) {
        _uiState.update { it.copy(category = category) }
    }

    fun onDateChange(date: Long) {
        _uiState.update { it.copy(date = date) }
    }
}

data class ManageUiState(
    val amount: Double = 0.0,
    val note: String = "",
    val category: Category? = null,
    val date: Long = System.currentTimeMillis(),
    val categories: List<Category> = emptyList()
)
