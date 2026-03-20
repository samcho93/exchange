package com.project.exchange.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeApiService {

    @GET("latest")
    suspend fun getLatestRates(
        @Query("from") from: String,
        @Query("to") to: String
    ): ExchangeApiResponse
}
