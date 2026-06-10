package com.khayrul.personalExpenseTracker.di

import com.khayrul.personalExpenseTracker.core.ResourceProvider
import com.khayrul.personalExpenseTracker.data.db.ExpenseTrackerDb
import com.khayrul.personalExpenseTracker.data.repo.CategoryRepo
import com.khayrul.personalExpenseTracker.data.repo.CategoryRepoImpl
import com.khayrul.personalExpenseTracker.data.repo.ExpenseRepo
import com.khayrul.personalExpenseTracker.data.repo.ExpenseRepoImpl
import com.khayrul.personalExpenseTracker.ui.screens.home.HomeViewModel
import com.khayrul.personalExpenseTracker.ui.screens.manage.add.AddExpenseViewModel
import com.khayrul.personalExpenseTracker.ui.screens.manage.edit.EditExpenseViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<ExpenseTrackerDb> {
        ExpenseTrackerDb.getInstance(androidContext())
    }

    single {
        get<ExpenseTrackerDb>().expenseDao()
    }

    single {
        get<ExpenseTrackerDb>().categoryDao()
    }

    single<ExpenseRepo> {
        ExpenseRepoImpl(get())
    }

    single<CategoryRepo> {
        CategoryRepoImpl(get())
    }

    viewModelOf(::HomeViewModel)

    viewModelOf(::AddExpenseViewModel)

    viewModelOf(::EditExpenseViewModel)

    single {
        ResourceProvider(androidContext())
    }
}
