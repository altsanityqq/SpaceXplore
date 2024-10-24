package com.example.spacexandroidapplication.ui.launches

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    val state by viewModel.state.collectAsState()
    val listState = rememberLazyListState()

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
            val successState = state as LaunchesState.Success

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState
            ) {
                items(successState.launches) { launch ->
                    LaunchItem(launch) {
                        onLaunchItemSelected(launch)
                    }
                }

                if (!successState.isLastPage && successState.isLoading) {
                    item {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }

        is LaunchesState.Idle -> {
        }
    }
}