package com.example.spacexandroidapplication.ui.launchDetail

import com.example.spacexandroidapplication.ui.model.PayloadUIModel

sealed class LaunchDetailState {
    object Loading : LaunchDetailState()
    data class Success(val payloads: List<PayloadUIModel>) : LaunchDetailState()
    data class Error(val message: String) : LaunchDetailState()
    object Idle : LaunchDetailState()
}
