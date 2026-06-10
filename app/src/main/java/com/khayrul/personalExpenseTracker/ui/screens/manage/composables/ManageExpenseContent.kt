package com.khayrul.personalExpenseTracker.ui.screens.manage.composables


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.khayrul.personalExpenseTracker.data.model.Category
import com.khayrul.personalExpenseTracker.ui.utils.DateUtils.millisToDateString
import androidx.compose.ui.res.stringResource
import com.khayrul.personalExpenseTracker.R
import com.khayrul.personalExpenseTracker.ui.screens.manage.base.ManageUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageExpenseContent(
    uiState: ManageUiState,
    onAmountChange: (Double) -> Unit,
    onNoteChange: (String) -> Unit,
    onCategoryChange: (Category) -> Unit,
    onDateChange: (Long) -> Unit,
    onSaveClick: () -> Unit,
    saveButtonText: String
) {
    var showDatePicker by remember { mutableStateOf(false) }
    var amountText by rememberSaveable { mutableStateOf("") }
    var hasInitializedAmount by rememberSaveable { mutableStateOf(false) }

    if (!hasInitializedAmount && uiState.amount > 0) {
        amountText = uiState.amount.toString()
        hasInitializedAmount = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CategoryDropdown(
            selectedCategory = uiState.category,
            categories = uiState.categories,
            onCategorySelected = onCategoryChange
        )

        OutlinedTextField(
            value = amountText,
            onValueChange = { value ->
                amountText = value
                value.toDoubleOrNull()?.let { onAmountChange(it) }
            },
            label = { Text(stringResource(R.string.amount)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            prefix = { Text("$") },
            shape = RoundedCornerShape(12.dp)
        )

        OutlinedTextField(
            value = millisToDateString(uiState.date),
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(R.string.date)) },
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(Icons.Default.CalendarToday, contentDescription = stringResource(R.string.select_date))
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        OutlinedTextField(
            value = uiState.note,
            onValueChange = onNoteChange,
            label = { Text(stringResource(R.string.note)) },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(Modifier.weight(1f))

        Button(
            onClick = onSaveClick,
            modifier = Modifier.fillMaxWidth()
                .height(56.dp),
            enabled = uiState.category != null && uiState.amount > 0,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(saveButtonText, style = MaterialTheme.typography.titleMedium)
        }
    }

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = uiState.date)
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { onDateChange(it) }
                    showDatePicker = false
                }) {
                    Text(stringResource(R.string.ok))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}