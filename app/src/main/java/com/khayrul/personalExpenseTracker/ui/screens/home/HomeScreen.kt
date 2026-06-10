package com.khayrul.personalExpenseTracker.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.khayrul.personalExpenseTracker.ui.navHost.Screen
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.ui.res.stringResource
import com.khayrul.personalExpenseTracker.R
import com.khayrul.personalExpenseTracker.ui.screens.home.composables.HistoryContent
import com.khayrul.personalExpenseTracker.ui.screens.home.composables.OverviewContent

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(HomeUiState())
    HomeScreenContent(uiState, navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(uiState: HomeUiState, navController: NavController) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf(stringResource(R.string.overview), stringResource(R.string.history))
    val icons = listOf(Icons.Default.PieChart, Icons.Default.History)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.expense_tracker)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        bottomBar = {
            Column {
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                    thickness = 1.dp
                )
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                    tonalElevation = 0.dp
                ) {
                    tabs.forEachIndexed { index, title ->
                        NavigationBarItem(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            label = { Text(title) },
                            icon = { Icon(icons[index], contentDescription = null) }
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddExpense) },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_expense))
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedTab) {
                0 -> OverviewContent(uiState)
                1 -> HistoryContent(uiState, navController)
            }
        }
    }
}
