package com.project.exchange.data.repository

import com.project.exchange.data.local.ExchangeRateDao
import com.project.exchange.data.local.ExchangeRateEntity
import com.project.exchange.data.remote.ExchangeApiService
import com.project.exchange.domain.model.ExchangeRate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExchangeRepository @Inject constructor(
    private val apiService: ExchangeApiService,
    private val dao: ExchangeRateDao
) {

    suspend fun getExchangeRates(base: String, targets: List<String>): Result<ExchangeRate> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getLatestRates(
                    from = base,
                    to = targets.joinToString(",")
                )
                val now = System.currentTimeMillis()

                val entities = response.rates.map { (currency, rate) ->
                    ExchangeRateEntity(
                        currencyPair = "${base}_$currency",
                        baseCurrency = base,
                        targetCurrency = currency,
                        rate = rate,
                        updatedAt = now
                    )
                }
                dao.insertRates(entities)

                Result.success(
                    ExchangeRate(
                        baseCurrency = base,
                        rates = response.rates,
                        timestamp = now
                    )
                )
            } catch (e: Exception) {
                val cached = dao.getRatesForBase(base)
                if (cached.isNotEmpty()) {
                    Result.success(
                        ExchangeRate(
                            baseCurrency = base,
                            rates = cached.associate { it.targetCurrency to it.rate },
                            timestamp = cached.first().updatedAt
                        )
                    )
                } else {
                    Result.failure(e)
                }
            }
        }
}
