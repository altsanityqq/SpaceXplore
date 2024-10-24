package com.example.spacexandroidapplication.data.api

import com.example.spacexandroidapplication.data.model.LaunchResponse
import com.example.spacexandroidapplication.data.model.PayloadNetworkModel
import com.example.spacexandroidapplication.data.model.RequestModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SpaceXApi {

    @POST("launches/query")
    suspend fun getLaunches(@Body request: RequestModel): LaunchResponse

    @GET("payloads/{id}")
    suspend fun getPayloadById(@Path("id") payloadId: String): PayloadNetworkModel

}