package com.khayrul.personalExpenseTracker.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel: ViewModel() {
    protected val _finish = MutableSharedFlow<Unit>()
    val finish = _finish.asSharedFlow()
}