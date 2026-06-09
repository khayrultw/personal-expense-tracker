package com.khayrul.personalExpenseTracker.data.repo

import com.khayrul.personalExpenseTracker.data.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepo {
    fun getAllCategories(): Flow<List<Category>>
    suspend fun getCategoryById(id: Int): Category?
    suspend fun insertCategory(category: Category)
    suspend fun deleteCategory(category: Category)
    suspend fun updateCategory(category: Category)
}