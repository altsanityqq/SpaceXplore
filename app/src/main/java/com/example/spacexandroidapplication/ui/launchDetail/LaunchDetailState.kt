package com.example.spacexandroidapplication.ui.launchDetail

import com.example.spacexandroidapplication.ui.model.PayloadUIModel

sealed class LaunchDetailState {
    data class Success(val payloads: List<PayloadUIModel>) : LaunchDetailState()
    object Error : LaunchDetailState()
    object Idle : LaunchDetailState()
}
