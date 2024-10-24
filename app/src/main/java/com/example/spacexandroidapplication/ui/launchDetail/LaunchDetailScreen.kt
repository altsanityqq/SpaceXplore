package com.example.spacexandroidapplication.ui.launchDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.spacexandroidapplication.data.repository.SpaceXRepository
import com.example.spacexandroidapplication.ui.model.LaunchUIModel
import com.example.spacexandroidapplication.ui.model.PayloadUIModel

@Composable
fun LaunchDetailScreen(
    launch: LaunchUIModel,
    repository: SpaceXRepository,
    viewModel: LaunchDetailViewModel = viewModel(
        factory = LaunchDetailViewModelFactory(repository)
    )
) {
    val state by viewModel.state.collectAsState()

    val payloads: MutableList<PayloadUIModel> = mutableListOf()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(LaunchDetailIntent.LoadPayloads(launch.payloadIds))
    }

    LaunchDetailColumn(launch, payloads)

    when (state) {
        is LaunchDetailState.Loading -> {
        }

        is LaunchDetailState.Success -> {
            payloads.addAll((state as LaunchDetailState.Success).payloads)
        }

        is LaunchDetailState.Error -> {
        }

        is LaunchDetailState.Idle -> {
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LaunchDetailColumn(launch: LaunchUIModel, payloads: MutableList<PayloadUIModel>) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = launch.name,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Date: ${launch.date}",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Status: ${launch.status}",
            style = MaterialTheme.typography.titleMedium
        )
        Image(
            painter = rememberImagePainter(
                launch.imageUrl,
                builder = {
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Text(
            text = "Rocket: ${launch.rocketName}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Details: ${launch.details}",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (launch.flickrImageUrls.isNotEmpty()) {
            Text(
                text = "Flickr Images:",
                style = MaterialTheme.typography.titleMedium
            )
            FlowRow(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally),
            ) {
                launch.flickrImageUrls.forEach { imageUrl ->
                    ImageCard(imageUrl)
                }
            }
        }

        if (launch.payloadIds.isNotEmpty()) {
            Text(
                text = "Payloads:",
                style = MaterialTheme.typography.titleMedium
            )
            LazyRow {
                items(payloads) { payload ->
                    PayloadCard(payload)
                }
            }
        }
    }
}

@Composable
fun ImageCard(imageUrl: String) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .size(160.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun PayloadCard(payload: PayloadUIModel) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
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
            TextRow(label = "Reused:", value = if (payload.reused == true) "Yes" else "No")
            TextRow(label = "Weight (kg):", value = payload.weight.toString())
            TextRow(label = "Orbit:", value = payload.orbit)

            if (payload.customers.isNotEmpty()) {
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
