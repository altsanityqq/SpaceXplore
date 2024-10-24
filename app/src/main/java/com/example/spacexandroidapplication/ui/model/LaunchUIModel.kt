package com.example.spacexandroidapplication.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LaunchUIModel(
    val id: String, // this is also the launch code
    val name: String,
    val date: String,
    val status: String,
    val imageUrl: String?,
    val rocketName: String,
    val payloadIds: List<String> = emptyList(),
    val details: String = "",
    val flickrImageUrls: List<String> = emptyList(), // list of images that shared on https://www.flickr.com/
) : Parcelable
