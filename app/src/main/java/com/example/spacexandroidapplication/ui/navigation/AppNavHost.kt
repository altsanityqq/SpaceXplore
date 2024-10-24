package com.example.spacexandroidapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.spacexandroidapplication.ui.launchDetail.LaunchDetailScreen
import com.example.spacexandroidapplication.ui.launches.LaunchesScreen
import com.example.spacexandroidapplication.ui.splash.SplashScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("launches") {
            LaunchesScreen(onLaunchItemSelected = { launchId ->
                navController.navigate("launchDetail/$launchId")
            })
        }
        composable(
            route = "launchDetail/{launchId}",
            arguments = listOf(navArgument("launchId") { type = NavType.StringType })
        ) { backStackEntry ->
            val launchId = backStackEntry.arguments?.getString("launchId") ?: ""
            LaunchDetailScreen()
        }
    }
}
