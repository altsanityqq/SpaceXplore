package com.example.spacexandroidapplication.ui.launchDetail

sealed class LaunchDetailIntent {
    data class LoadPayloads(val payloadIds: List<String>): LaunchDetailIntent()
}