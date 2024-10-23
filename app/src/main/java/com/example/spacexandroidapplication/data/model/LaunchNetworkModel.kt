package com.example.spacexandroidapplication.data.model

data class LaunchNetworkModel(
    val id: String,
    val name: String,
    val date_utc: String,
    val success: Boolean?,
    val rocket: String,
    val details: String?,
)