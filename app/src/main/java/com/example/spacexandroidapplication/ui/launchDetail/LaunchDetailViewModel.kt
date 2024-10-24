package com.example.spacexandroidapplication.ui.launchDetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spacexandroidapplication.data.repository.SpaceXRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LaunchDetailViewModel(
    private val repository: SpaceXRepository,
) : ViewModel() {
    private val _state = MutableStateFlow<LaunchDetailState>(LaunchDetailState.Idle)
    val state: StateFlow<LaunchDetailState> = _state.asStateFlow()

    fun handleIntent(intent: LaunchDetailIntent) {
        when (intent) {
            is LaunchDetailIntent.LoadPayloads -> loadPayloads(intent.payloadIds)
        }
    }

    private fun loadPayloads(payloadIds: List<String>) {
        viewModelScope.launch {
            try {
                val payloads = repository.fetchPayloads(payloadIds)
                Log.d("Bakayarou", "payloads = $payloads")
                _state.value = LaunchDetailState.Success(payloads)
            } catch (e: Exception) {
                Log.d("Bakayarou", "e = $e")
            }
        }
    }
}

class LaunchDetailViewModelFactory(private val repository: SpaceXRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LaunchDetailViewModel::class.java)) {
            return LaunchDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}