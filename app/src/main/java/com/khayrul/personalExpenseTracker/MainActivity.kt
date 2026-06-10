package com.khayrul.personalExpenseTracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.khayrul.personalExpenseTracker.ui.navHost.AppNavHost
import com.khayrul.personalExpenseTracker.ui.theme.PersonalexpensetrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PersonalexpensetrackerTheme {
                AppNavHost()
            }
        }
    }
}