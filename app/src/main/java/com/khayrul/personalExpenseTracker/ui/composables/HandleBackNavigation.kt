package com.khayrul.personalExpenseTracker.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.khayrul.personalExpenseTracker.ui.base.BaseViewModel

@Composable
fun HandleBackNavigation(
    viewModel: BaseViewModel,
    navController: NavController
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner, viewModel.finish) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.finish.collect {
                navController.popBackStack()
            }
        }
    }
}