package com.example.spacexandroidapplication.ui.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// TODO add repository usage and pagination handling
class LaunchesViewModel : ViewModel() {

    private val _state = MutableStateFlow(LaunchesState())
    val state: StateFlow<LaunchesState> get() = _state.asStateFlow()

    init {
        processIntent(LaunchesIntent.LoadLaunches)
    }

    fun processIntent(intent: LaunchesIntent) {
        when (intent) {
            is LaunchesIntent.LoadLaunches -> loadLaunches()
            is LaunchesIntent.LaunchSelected -> handleLaunchSelection(intent.launchId)
        }
    }

    fun handleLaunchSelection(launchId: String) {
        _state.value = _state.value.copy(selectedLaunchId = launchId)
    }

    private fun loadLaunches() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
        }
    }
}
