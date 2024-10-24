package com.example.spacexandroidapplication.data

import com.example.spacexandroidapplication.data.api.ApiConstants
import com.example.spacexandroidapplication.data.api.SpaceXApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: SpaceXApi by lazy { retrofit.create(SpaceXApi::class.java) }
}