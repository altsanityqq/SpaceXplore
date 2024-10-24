package com.example.spacexandroidapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.spacexandroidapplication.data.RetrofitInstance
import com.example.spacexandroidapplication.data.repository.SpaceXRepository
import com.example.spacexandroidapplication.ui.navigation.AppNavHost
import com.example.spacexandroidapplication.ui.theme.SpaceXAndroidApplicationTheme

class MainActivity : ComponentActivity() {

    private lateinit var repository: SpaceXRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceXAndroidApplicationTheme {
                val navController = rememberNavController()
                repository = SpaceXRepository(RetrofitInstance.api)
                AppNavHost(navController, repository)
            }
        }
    }
}