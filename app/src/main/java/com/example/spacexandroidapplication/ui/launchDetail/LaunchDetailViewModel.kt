package com.example.spacexandroidapplication.ui.launchDetail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LaunchDetailViewModel : ViewModel() {
    private val _state = MutableStateFlow(LaunchDetailState())
    val state: StateFlow<LaunchDetailState> = _state

    fun handleIntent(intent: LaunchDetailIntent) {
        when (intent) {
            is LaunchDetailIntent.LoadLaunchDetail -> loadLaunchDetail(intent.launchId)
            is LaunchDetailIntent.LoadPayloads -> loadPayloads(intent.payloadIds)
        }
    }

    private fun loadPayloads(payloadIds: List<String>) {
    }

    private fun loadLaunchDetail(launchId: String) {
    }
}
