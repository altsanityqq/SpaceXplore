package com.example.spacexandroidapplication.ui.model

data class PayloadUIModel(
    val id: String,
    val name: String,
    val type: String,
    val reused: Boolean?,
    val customers: List<String>,
    val weight: Double,
    val orbit: String
)

