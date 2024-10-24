package com.example.spacexandroidapplication.ui.launches

import com.example.spacexandroidapplication.ui.model.LaunchUIModel

data class LaunchesState(
    val launches: List<LaunchUIModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedLaunchId: String? = null
)