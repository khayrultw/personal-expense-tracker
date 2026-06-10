package com.khayrul.personalExpenseTracker.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.khayrul.personalExpenseTracker.data.model.Expense
import com.khayrul.personalExpenseTracker.data.model.ExpenseWithCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM Expense ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<ExpenseWithCategory>>

    @Query("SELECT * FROM Expense WHERE id = :id")
    suspend fun getExpenseById(id: Int): ExpenseWithCategory?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Update
    suspend fun updateExpense(expense: Expense)

    @Query("UPDATE Expense SET categoryId = :newCategoryId WHERE categoryId = :oldCategoryId")
    suspend fun updateCategoryForExpenses(oldCategoryId: Int, newCategoryId: Int)
}