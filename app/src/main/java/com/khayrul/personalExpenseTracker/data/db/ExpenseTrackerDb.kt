package com.khayrul.personalExpenseTracker.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.khayrul.personalExpenseTracker.data.model.Category
import com.khayrul.personalExpenseTracker.data.model.Expense


@Database(
    entities = [Expense::class, Category::class],
    version = 1,
    exportSchema = false
)
abstract class ExpenseTrackerDb: RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        const val NAME = "personal_expense_tracker.db"
        @Volatile
        private var instance: ExpenseTrackerDb? = null

        fun getInstance(context: Context): ExpenseTrackerDb {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseTrackerDb::class.java,
                    NAME
                ).createFromAsset("expense_tracker.db")
                    .build().also {
                    instance = it
                }
            }
        }
    }
}