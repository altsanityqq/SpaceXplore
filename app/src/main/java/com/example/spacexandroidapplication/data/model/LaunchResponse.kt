package com.example.spacexandroidapplication.data.model

data class LaunchResponse(
    val docs: List<LaunchNetworkModel>,
    val totalDocs: Int,
    val limit: Int,
    val totalPages: Int,
    val page: Int,
    val pagingCounter: Int,
    val hasPrevPage: Boolean,
    val hasNextPage: Boolean
)