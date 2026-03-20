package com.project.exchange.ui.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.exchange.data.repository.ExchangeRepository
import com.project.exchange.domain.model.Currency
import com.project.exchange.domain.usecase.ConvertCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

data class ExchangeUiState(
    val selectedCurrencies: List<String> = listOf("USD", "KRW", "EUR", "JPY"),
    val amounts: Map<String, String> = mapOf("USD" to "1"),
    val rates: Map<String, Double> = emptyMap(),
    val baseCurrency: String = "USD",
    val isLoading: Boolean = false,
    val error: String? = null,
    val lastUpdated: String? = null,
    val activeCurrency: String = "USD"
)

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    private val repository: ExchangeRepository,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase,
    private val prefs: SharedPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExchangeUiState())
    val uiState: StateFlow<ExchangeUiState> = _uiState.asStateFlow()

    private var refreshJob: Job? = null

    init {
        loadSavedCurrencies()
        fetchRates()
        startAutoRefresh()
    }

    private fun loadSavedCurrencies() {
        val saved = prefs.getString(KEY_SELECTED_CURRENCIES, null)
        if (saved != null) {
            val currencies = saved.split(",").filter { it.isNotEmpty() }
            if (currencies.size >= 2) {
                _uiState.update {
                    it.copy(
                        selectedCurrencies = currencies,
                        activeCurrency = currencies.first(),
                        amounts = mapOf(currencies.first() to "1")
                    )
                }
            }
        }
    }

    private fun saveCurrencies(currencies: List<String>) {
        prefs.edit().putString(KEY_SELECTED_CURRENCIES, currencies.joinToString(",")).apply()
    }

    fun onAmountChanged(currencyCode: String, amount: String) {
        val sanitized = amount.filter { it.isDigit() || it == '.' }
        val parsedAmount = sanitized.toDoubleOrNull() ?: 0.0
        val state = _uiState.value

        val newAmounts = mutableMapOf<String, String>()
        newAmounts[currencyCode] = sanitized

        state.selectedCurrencies.filter { it != currencyCode }.forEach { target ->
            val converted = convertCurrencyUseCase(
                amount = parsedAmount,
                fromCurrency = currencyCode,
                toCurrency = target,
                baseCurrency = state.baseCurrency,
                rates = state.rates
            )
            newAmounts[target] = if (parsedAmount == 0.0) "" else formatAmount(converted, target)
        }

        _uiState.update {
            it.copy(amounts = newAmounts, activeCurrency = currencyCode)
        }
    }

    fun onCurrencyChanged(index: Int, newCurrencyCode: String) {
        _uiState.update { state ->
            val currencies = state.selectedCurrencies.toMutableList()
            val oldCode = currencies[index]
            currencies[index] = newCurrencyCode

            val newAmounts = state.amounts.toMutableMap()
            newAmounts.remove(oldCode)

            state.copy(selectedCurrencies = currencies, amounts = newAmounts)
        }
        saveCurrencies(_uiState.value.selectedCurrencies)
        fetchRates()
    }

    fun addCurrency(code: String) {
        _uiState.update { state ->
            if (state.selectedCurrencies.size >= 4) return@update state
            state.copy(selectedCurrencies = state.selectedCurrencies + code)
        }
        saveCurrencies(_uiState.value.selectedCurrencies)
        fetchRates()
    }

    fun removeCurrency(index: Int) {
        _uiState.update { state ->
            if (state.selectedCurrencies.size <= 2) return@update state
            val currencies = state.selectedCurrencies.toMutableList()
            val removed = currencies.removeAt(index)
            val newAmounts = state.amounts.toMutableMap()
            newAmounts.remove(removed)

            val newBase = if (state.baseCurrency == removed) currencies.first() else state.baseCurrency
            val newActive = if (state.activeCurrency == removed) currencies.first() else state.activeCurrency

            state.copy(
                selectedCurrencies = currencies,
                amounts = newAmounts,
                baseCurrency = newBase,
                activeCurrency = newActive
            )
        }
        saveCurrencies(_uiState.value.selectedCurrencies)
        fetchRates()
    }

    fun refreshRates() {
        fetchRates()
    }

    private fun fetchRates() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val state = _uiState.value
            val base = state.selectedCurrencies.first()
            val targets = state.selectedCurrencies.drop(1)

            if (targets.isEmpty()) {
                _uiState.update { it.copy(isLoading = false) }
                return@launch
            }

            val result = repository.getExchangeRates(base, targets)

            result.onSuccess { exchangeRate ->
                _uiState.update { current ->
                    val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                    val updated = current.copy(
                        rates = exchangeRate.rates,
                        baseCurrency = base,
                        isLoading = false,
                        lastUpdated = dateFormat.format(Date(exchangeRate.timestamp)),
                        error = null
                    )
                    updated
                }
                // Recalculate amounts with new rates
                val currentState = _uiState.value
                val activeAmount = currentState.amounts[currentState.activeCurrency]
                if (!activeAmount.isNullOrEmpty()) {
                    onAmountChanged(currentState.activeCurrency, activeAmount)
                }
            }

            result.onFailure { e ->
                _uiState.update {
                    it.copy(isLoading = false, error = e.message ?: "Failed to fetch rates")
                }
            }
        }
    }

    private fun startAutoRefresh() {
        refreshJob?.cancel()
        refreshJob = viewModelScope.launch {
            while (true) {
                delay(60_000L)
                fetchRates()
            }
        }
    }

    private fun formatAmount(amount: Double, currencyCode: String): String {
        val decimals = when (currencyCode) {
            "KRW", "JPY", "HUF", "IDR" -> 0
            else -> 2
        }
        return String.format(Locale.US, "%.${decimals}f", amount)
    }

    companion object {
        private const val KEY_SELECTED_CURRENCIES = "selected_currencies"
    }
}
