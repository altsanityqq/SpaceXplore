package com.example.spacexandroidapplication.ui.launchDetail

sealed class LaunchDetailIntent {
    data class LoadLaunchDetail(val launchId: String) : LaunchDetailIntent()
    data class LoadPayloads(val payloadIds: List<String>): LaunchDetailIntent()
}