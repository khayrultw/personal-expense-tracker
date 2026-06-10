package com.khayrul.personalExpenseTracker.ui.screens.manage.edit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.khayrul.personalExpenseTracker.ui.composables.HandleBackNavigation
import org.koin.androidx.compose.koinViewModel
import androidx.compose.ui.res.stringResource
import com.khayrul.personalExpenseTracker.R
import com.khayrul.personalExpenseTracker.ui.screens.manage.composables.ManageExpenseContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditExpenseScreen(
    navController: NavController,
    viewModel: EditExpenseViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HandleBackNavigation(viewModel, navController)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.edit_expense)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.back))
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            ManageExpenseContent(
                uiState = uiState,
                onAmountChange = viewModel::onAmountChange,
                onNoteChange = viewModel::onNoteChange,
                onCategoryChange = viewModel::onCategoryChange,
                onDateChange = viewModel::onDateChange,
                onSaveClick = viewModel::update,
                saveButtonText = stringResource(R.string.update_expense)
            )
        }
    }
}
