package com.khayrul.personalExpenseTracker.ui.navHost

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.khayrul.personalExpenseTracker.ui.screens.home.HomeScreen
import com.khayrul.personalExpenseTracker.ui.screens.manage.add.AddExpenseScreen
import com.khayrul.personalExpenseTracker.ui.screens.manage.edit.EditExpenseScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home
    ) {
        composable<Screen.Home> {
            HomeScreen(navController = navController)
        }
        composable<Screen.AddExpense> {
            AddExpenseScreen(navController = navController)
        }
        composable<Screen.EditExpense> {
            EditExpenseScreen(
                navController = navController
            )
        }
    }
}
