package com.example.spacexandroidapplication.ui.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spacexandroidapplication.data.model.toLaunchUIModel
import com.example.spacexandroidapplication.data.repository.SpaceXRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LaunchesViewModel(private val repository: SpaceXRepository) : ViewModel() {

    private val _state = MutableStateFlow<LaunchesState>(LaunchesState.Idle)
    val state: StateFlow<LaunchesState> = _state.asStateFlow()

    private var currentPage = 1
    private var isLoading = false
    private var isLastPage = false

    init {
        handleIntent(LaunchesIntent.LoadNextPage)
    }

    fun handleIntent(intent: LaunchesIntent) {
        when (intent) {
            is LaunchesIntent.LoadNextPage -> loadNextPage()
            is LaunchesIntent.Retry -> retryLoading()
        }
    }

    private fun loadNextPage() {
        if (isLoading || isLastPage) return

        isLoading = true
        _state.value = LaunchesState.Loading

        viewModelScope.launch {
            try {
                val response = repository.fetchLaunches(page = currentPage)
                isLastPage = response.hasNextPage == false
                val newLaunches = response.docs.map { it.toLaunchUIModel() }
                val successState = LaunchesState.Success(
                    isLastPage = isLastPage,
                    isLoading = false
                )
                successState.launches.addAll(newLaunches)
                _state.value = successState

                currentPage++
            } catch (e: Exception) {
                _state.value = LaunchesState.Error(e.message ?: "Unknown error")
            } finally {
                isLoading = false
            }
        }
    }

    private fun retryLoading() {
        currentPage = 1
        isLastPage = false
        handleIntent(LaunchesIntent.LoadNextPage)
    }
}

class LaunchesViewModelFactory(private val repository: SpaceXRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LaunchesViewModel::class.java)) {
            return LaunchesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}