package com.example.spacexandroidapplication.ui.launches

import com.example.spacexandroidapplication.ui.model.LaunchUIModel

sealed class LaunchesState {
    object Loading : LaunchesState()
    data class Success(
        val launches: MutableList<LaunchUIModel> = mutableListOf(),
        val isLastPage: Boolean,
        val isLoading: Boolean = false
    ) : LaunchesState()

    data class Error(val message: String) : LaunchesState()
    object Idle : LaunchesState()
}
