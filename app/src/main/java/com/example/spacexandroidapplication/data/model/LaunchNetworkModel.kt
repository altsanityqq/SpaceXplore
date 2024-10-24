package com.example.spacexandroidapplication.data.model

import com.example.spacexandroidapplication.ui.model.LaunchUIModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

data class LaunchNetworkModel(
    val links: Links,
    val id: String,
    val name: String,
    val date_utc: String,
    val success: Boolean?,
    val payloads: List<String>,
    val rocket: String,
    val details: String?,
)

data class Links(
    val patch: Patch,
    val flickr: Flickr,
)

data class Patch(
    val small: String,
    val large: String
)

data class Flickr(
    val small: List<String>,
    val original: List<String>
)

fun LaunchNetworkModel.toLaunchUIModel(): LaunchUIModel {
    return LaunchUIModel(
        id = this.id,
        name = this.name,
        date = formatDate(this.date_utc),
        status = if (this.success == true) "Success" else "Failed",
        imageUrl = this.links.patch.large,
        rocketName = this.rocket,
        payloadIds = this.payloads,
        details = this.details ?: "",
        flickrImageUrls = this.links.flickr.original
    )
}

fun formatDate(input: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")

    val date: Date? = inputFormat.parse(input)

    val outputFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault())

    return date?.let { outputFormat.format(it) } ?: "Invalid date"
}