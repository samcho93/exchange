package com.project.exchange.data.remote

data class ExchangeApiResponse(
    val amount: Double,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)
