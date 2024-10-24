package com.example.spacexandroidapplication.data.repository

import com.example.spacexandroidapplication.data.api.SpaceXApi
import com.example.spacexandroidapplication.data.model.LaunchResponse
import com.example.spacexandroidapplication.data.model.RequestModel
import com.example.spacexandroidapplication.data.model.toPayloadUIModel
import com.example.spacexandroidapplication.ui.model.PayloadUIModel

class SpaceXRepository(private val api: SpaceXApi) {

    suspend fun fetchLaunches(page: Int): LaunchResponse {
        return api.getLaunches(RequestModel(options = RequestModel.Options(page, 20)))
    }

    suspend fun fetchPayloads(payloadIds: List<String>): List<PayloadUIModel> {
        val items = mutableListOf<PayloadUIModel>()
        for (id in payloadIds) {
            val payload = api.getPayloadById(id)
            items.add(payload.toPayloadUIModel())
        }
        return items
    }
}
