package com.khayrul.personalExpenseTracker.data.repo

import com.khayrul.personalExpenseTracker.data.db.CategoryDao
import com.khayrul.personalExpenseTracker.data.model.Category
import kotlinx.coroutines.flow.Flow

class CategoryRepoImpl(
    private val categoryDao: CategoryDao
): CategoryRepo {
    override fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories()
    }

    override suspend fun getCategoryById(id: Int): Category? {
        return categoryDao.getCategoryById(id)
    }

    override suspend fun insertCategory(category: Category) {
        return categoryDao.insertCategory(category)
    }

    override suspend fun deleteCategory(category: Category) {
        return categoryDao.deleteCategory(category)
    }

    override suspend fun updateCategory(category: Category) {
        return categoryDao.updateCategory(category)
    }
}