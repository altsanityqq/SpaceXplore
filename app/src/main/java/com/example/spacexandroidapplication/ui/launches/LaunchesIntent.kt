package com.example.spacexandroidapplication.ui.launches

sealed class LaunchesIntent {
    object LoadLaunches : LaunchesIntent()
    data class LaunchSelected(val launchId: String) : LaunchesIntent()
}