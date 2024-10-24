package com.example.spacexandroidapplication.ui.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spacexandroidapplication.data.model.toLaunchUIModel
import com.example.spacexandroidapplication.data.repository.SpaceXRepository
import com.example.spacexandroidapplication.ui.model.LaunchUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LaunchesViewModel(private val repository: SpaceXRepository) : ViewModel() {

    private val _items = MutableStateFlow<List<LaunchUIModel>>(emptyList())
    val items: StateFlow<List<LaunchUIModel>> = _items

    private val _state = MutableStateFlow<LaunchesState>(LaunchesState.Idle)
    val state: StateFlow<LaunchesState> = _state.asStateFlow()

    private var currentPage = 1
    private var isLoading = false
    private var isLastPage = false

    init {
        handleIntent(LaunchesIntent.LoadFirstPage)
    }

    fun handleIntent(intent: LaunchesIntent) {
        when (intent) {
            is LaunchesIntent.LoadFirstPage -> loadFirstPage()
            is LaunchesIntent.LoadNextPage -> loadNextPage()
            is LaunchesIntent.Retry -> retryLoading()
        }
    }

    private fun loadFirstPage() {
        loadNextPage()
        _state.value = LaunchesState.Loading
    }

    private fun loadNextPage() {
        if (isLoading || isLastPage) return
        isLoading = true
        viewModelScope.launch {
            try {
                val response = repository.fetchLaunches(page = currentPage)
                if (!response.hasNextPage) {
                    isLastPage = true

                }
                _items.value += response.docs.map { it.toLaunchUIModel() }
                _state.value = LaunchesState.Success

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
        handleIntent(LaunchesIntent.LoadFirstPage)
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