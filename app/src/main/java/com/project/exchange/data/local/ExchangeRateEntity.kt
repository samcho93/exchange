package com.project.exchange.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_rates")
data class ExchangeRateEntity(
    @PrimaryKey
    val currencyPair: String,
    val baseCurrency: String,
    val targetCurrency: String,
    val rate: Double,
    val updatedAt: Long
)
