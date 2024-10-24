package com.example.spacexandroidapplication.data.api

import com.example.spacexandroidapplication.data.model.LaunchResponse
import com.example.spacexandroidapplication.data.model.PayloadResponse
import retrofit2.http.POST

interface SpaceXApi {

    @POST("launches/query")
    suspend fun getLaunches(): LaunchResponse

    @POST("payloads/query")
    suspend fun getPayloads(): PayloadResponse

}