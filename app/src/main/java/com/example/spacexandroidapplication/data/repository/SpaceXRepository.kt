package com.example.spacexandroidapplication.data.repository

import com.example.spacexandroidapplication.data.api.SpaceXApi
import com.example.spacexandroidapplication.data.model.LaunchResponse
import com.example.spacexandroidapplication.data.model.PayloadResponse

class SpaceXRepository(private val api: SpaceXApi) {

    suspend fun fetchLaunches(): LaunchResponse {
        return api.getLaunches()
    }

    suspend fun fetchPayloads(): PayloadResponse {
        return api.getPayloads()
    }

}