package com.example.spacexandroidapplication.data.model

import com.google.gson.annotations.SerializedName

data class RequestModel(
    @SerializedName("query") val query: Any = Any(),
    @SerializedName("options") val options: Options
) {
    data class Options(
        @SerializedName("page") val page: Int,
        @SerializedName("limit") val limit: Int
    )
}
