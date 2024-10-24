package com.example.spacexandroidapplication.ui.launches

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spacexandroidapplication.data.repository.SpaceXRepository
import com.example.spacexandroidapplication.ui.components.ErrorScreen
import com.example.spacexandroidapplication.ui.components.LoadingIndicator
import com.example.spacexandroidapplication.ui.model.LaunchUIModel

@Composable
fun LaunchesScreen(
    repository: SpaceXRepository,
    onLaunchItemSelected: (LaunchUIModel) -> Unit,
    viewModel: LaunchesViewModel = viewModel(factory = LaunchesViewModelFactory(repository)),
) {
    val items by viewModel.items.collectAsState()
    val state by viewModel.state.collectAsState()

    when (state) {
        is LaunchesState.Loading -> {
            LoadingIndicator()
        }

        is LaunchesState.Error -> {
            ErrorScreen {
                viewModel.handleIntent(LaunchesIntent.Retry)
            }
        }

        is LaunchesState.Success -> {
            PaginatedList(
                items = items,
                onLaunchItemSelected
            ) {
                viewModel.handleIntent(LaunchesIntent.LoadNextPage)
            }
        }

        is LaunchesState.Idle -> {
        }
    }
}