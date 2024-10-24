package com.example.spacexandroidapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spacexandroidapplication.data.repository.SpaceXRepository
import com.example.spacexandroidapplication.ui.launchDetail.LaunchDetailScreen
import com.example.spacexandroidapplication.ui.launches.LaunchesScreen
import com.example.spacexandroidapplication.ui.model.LaunchUIModel
import com.example.spacexandroidapplication.ui.splash.SplashScreen

@Composable
fun AppNavHost(navController: NavHostController, repository: SpaceXRepository) {
    NavHost(navController = navController, startDestination = "launches") {
        composable("splash") { SplashScreen(navController) }
        composable("launches") {
            LaunchesScreen(onLaunchItemSelected = { launch ->
                navController.currentBackStackEntry?.savedStateHandle?.set("launch", launch)
                navController.navigate("launchDetails")
            }, repository = repository)
        }
        composable("launchDetails") { backStackEntry ->
            val launch = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<LaunchUIModel>("launch")

            launch?.let { LaunchDetailScreen(launch = it, repository = repository) }
        }
    }
}
