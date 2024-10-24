package com.example.spacexandroidapplication.ui.launchDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.spacexandroidapplication.ui.launches.LoadingIndicator
import com.example.spacexandroidapplication.ui.model.LaunchUIModel
import com.example.spacexandroidapplication.ui.model.PayloadUIModel

@Composable
fun LaunchDetailScreen(
    viewModel: LaunchDetailViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        //viewModel.handleIntent(LaunchDetailIntent.LoadPayloads(launchUIModel.payloadIds))
    }

    when {
        state.isLoading -> {
            LoadingIndicator()
        }

        state.error != null -> {
            //ErrorScreen(state.error)
        }

        else -> state.launch?.let { LaunchDetailContent(it) }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LaunchDetailContent(launch: LaunchUIModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = launch.name, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = launch.details)

        FlowRow(
            modifier = Modifier.padding(vertical = 8.dp),
        ) {
            launch.flickrImageUrls.forEach { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }

        Text(text = "Payloads:", style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun PayloadsList(payloads: List<PayloadUIModel>) {
    LazyColumn {
        items(payloads) { payload ->
            PayloadItem(payload)
        }
    }
}

@Composable
fun PayloadItem(payload: PayloadUIModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = payload.name ?: "Unknown Payload",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )

            TextRow(label = "Type:", value = payload.type)
            TextRow(label = "Reused:", value = payload.reused?.toString())
            TextRow(label = "Manufacturer:", value = payload.manufacturer)
            TextRow(label = "Nationality:", value = payload.nationality)
            TextRow(label = "Weight (kg):", value = payload.weight?.toString())
            TextRow(label = "Orbit:", value = payload.orbit)

            if (!payload.customers.isNullOrEmpty()) {
                Text(
                    text = "Customers:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                payload.customers.forEach { customer ->
                    Text(
                        text = "- $customer",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Composable
fun TextRow(label: String, value: String?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = value ?: "N/A",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
