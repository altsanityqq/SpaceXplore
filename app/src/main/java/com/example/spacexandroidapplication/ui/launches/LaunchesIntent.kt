package com.example.spacexandroidapplication.ui.launches

sealed class LaunchesIntent {
    object LoadFirstPage : LaunchesIntent()
    object LoadNextPage : LaunchesIntent()
    object Retry : LaunchesIntent()
}