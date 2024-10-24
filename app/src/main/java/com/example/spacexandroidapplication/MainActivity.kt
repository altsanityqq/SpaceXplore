package com.example.spacexandroidapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.spacexandroidapplication.ui.navigation.AppNavHost
import com.example.spacexandroidapplication.ui.theme.SpaceXAndroidApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceXAndroidApplicationTheme {
                val navController = rememberNavController()
                AppNavHost(navController)
            }
        }
    }
}