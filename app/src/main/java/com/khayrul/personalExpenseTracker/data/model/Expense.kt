package com.khayrul.personalExpenseTracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val categoryId: Int,
    val amount: Double,
    val date: Long,
    val note: String,
    val createAt: Long = System.currentTimeMillis()
)