package com.project.exchange.domain.usecase

import javax.inject.Inject

class ConvertCurrencyUseCase @Inject constructor() {

    operator fun invoke(
        amount: Double,
        fromCurrency: String,
        toCurrency: String,
        baseCurrency: String,
        rates: Map<String, Double>
    ): Double {
        if (fromCurrency == toCurrency) return amount
        if (amount == 0.0) return 0.0

        val fromRate = if (fromCurrency == baseCurrency) 1.0 else rates[fromCurrency] ?: return 0.0
        val toRate = if (toCurrency == baseCurrency) 1.0 else rates[toCurrency] ?: return 0.0

        return amount * toRate / fromRate
    }
}
