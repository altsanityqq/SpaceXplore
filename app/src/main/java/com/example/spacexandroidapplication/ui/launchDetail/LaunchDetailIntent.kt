package com.example.spacexandroidapplication.ui.launchDetail

sealed class LaunchDetailIntent {
    data class LoadPayloads(val payloadIds: List<String>) : LaunchDetailIntent()
    data class Retry(val payloadIds: List<String>) : LaunchDetailIntent()
}