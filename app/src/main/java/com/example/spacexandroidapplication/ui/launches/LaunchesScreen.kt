package com.example.spacexandroidapplication.ui.launches

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

//TODO Add pagination handling
@Composable
fun LaunchesScreen(
    viewModel: LaunchesViewModel = viewModel(),
    onLaunchItemSelected: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.processIntent(LaunchesIntent.LoadLaunches)
    }

    when {
        state.isLoading -> {
            LoadingIndicator()
        }

        state.error != null -> {
            //ErrorScreen(state.error)
        }

        else -> {
            LazyColumn {
                items(state.launches) { launch ->
                    LaunchItem(launch) {
                        onLaunchItemSelected(launch.id)
                    }
                }
            }
        }
    }

}