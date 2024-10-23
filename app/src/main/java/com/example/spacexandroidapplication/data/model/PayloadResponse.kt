package com.example.spacexandroidapplication.data.model

data class PayloadResponse(
    val docs: List<PayloadNetworkModel>,
    val totalDocs: Int,
    val limit: Int,
    val totalPages: Int,
    val page: Int,
    val pagingCounter: Int,
    val hasPrevPage: Boolean,
    val hasNextPage: Boolean
)