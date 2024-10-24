package com.example.spacexandroidapplication.ui.launches

sealed class LaunchesState {
    object Loading : LaunchesState()
    object Success : LaunchesState()
    data class Error(val message: String) : LaunchesState()
    object Idle : LaunchesState()
}
