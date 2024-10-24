package com.example.spacexandroidapplication.data.model

import com.example.spacexandroidapplication.ui.model.PayloadUIModel

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

fun PayloadNetworkModel.toPayloadUIModel(): PayloadUIModel {
    return PayloadUIModel(
        id = this.id,
        name = this.name ?: "",
        type = this.type ?: "",
        reused = this.reused ?: false,
        customers = this.customers ?: emptyList(),
        weight = this.mass_kg ?: 0.0,
        orbit = this.orbit ?: ""
    )
}