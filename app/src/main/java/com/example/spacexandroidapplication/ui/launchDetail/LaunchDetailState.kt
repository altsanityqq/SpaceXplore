package com.example.spacexandroidapplication.ui.launchDetail

import com.example.spacexandroidapplication.ui.model.LaunchUIModel
import com.example.spacexandroidapplication.ui.model.PayloadUIModel

data class LaunchDetailState(
    val isLoading: Boolean = true,
    val launch: LaunchUIModel? = null,
    val payloads: List<PayloadUIModel> = emptyList(),
    val error: String? = null
)
