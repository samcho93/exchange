package com.project.exchange.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.exchange.ui.components.CurrencyRow
import com.project.exchange.ui.components.CurrencySelector
import com.project.exchange.ui.viewmodel.ExchangeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeScreen(
    viewModel: ExchangeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var showCurrencySelector by remember { mutableStateOf(false) }
    var selectorTargetIndex by remember { mutableIntStateOf(-1) }

    LaunchedEffect(state.error) {
        state.error?.let {
            snackbarHostState.showSnackbar(it)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("환율 계산기") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    IconButton(onClick = { viewModel.refreshRates() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "새로고침")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            if (state.selectedCurrencies.size < 4) {
                FloatingActionButton(
                    onClick = {
                        selectorTargetIndex = -1
                        showCurrencySelector = true
                    }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "통화 추가")
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (state.isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            state.lastUpdated?.let { time ->
                Text(
                    text = "마지막 업데이트: $time",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            state.selectedCurrencies.forEachIndexed { index, currencyCode ->
                CurrencyRow(
                    currencyCode = currencyCode,
                    amount = state.amounts[currencyCode] ?: "",
                    isActive = currencyCode == state.activeCurrency,
                    canDelete = state.selectedCurrencies.size > 2,
                    onAmountChanged = { viewModel.onAmountChanged(currencyCode, it) },
                    onCurrencyClick = {
                        selectorTargetIndex = index
                        showCurrencySelector = true
                    },
                    onDelete = { viewModel.removeCurrency(index) }
                )
            }

            if (state.rates.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "1 ${state.baseCurrency} = " +
                        state.rates.entries.joinToString(" | ") { (code, rate) ->
                            "${formatRate(rate)} $code"
                        },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    if (showCurrencySelector) {
        CurrencySelector(
            excludedCurrencies = state.selectedCurrencies,
            onCurrencySelected = { code ->
                if (selectorTargetIndex >= 0) {
                    viewModel.onCurrencyChanged(selectorTargetIndex, code)
                } else {
                    viewModel.addCurrency(code)
                }
                showCurrencySelector = false
            },
            onDismiss = { showCurrencySelector = false }
        )
    }
}

private fun formatRate(rate: Double): String =
    if (rate > 100) String.format("%.0f", rate)
    else if (rate > 1) String.format("%.2f", rate)
    else String.format("%.4f", rate)
