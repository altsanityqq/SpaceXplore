package com.example.spacexandroidapplication.data.model

data class PayloadNetworkModel(
    val id: String,
    val name: String?,
    val type: String?,
    val reused: Boolean?,
    val customers: List<String>?,
    val mass_kg: Double?,
    val orbit: String?,
    val manufacturer: String?,
    val nationality: String?
)