package com.project.exchange.ui.viewmodel;

import android.content.SharedPreferences;
import androidx.lifecycle.ViewModel;
import com.project.exchange.data.repository.ExchangeRepository;
import com.project.exchange.domain.model.Currency;
import com.project.exchange.domain.usecase.ConvertCurrencyUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u0000 \'2\u00020\u0001:\u0001\'B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0013H\u0002J\u0018\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0015H\u0002J\b\u0010\u001b\u001a\u00020\u0013H\u0002J\u0016\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0015J\u0016\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0015J\u0006\u0010!\u001a\u00020\u0013J\u000e\u0010\"\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001fJ\u0016\u0010#\u001a\u00020\u00132\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00150%H\u0002J\b\u0010&\u001a\u00020\u0013H\u0002R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006("}, d2 = {"Lcom/project/exchange/ui/viewmodel/ExchangeViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/project/exchange/data/repository/ExchangeRepository;", "convertCurrencyUseCase", "Lcom/project/exchange/domain/usecase/ConvertCurrencyUseCase;", "prefs", "Landroid/content/SharedPreferences;", "(Lcom/project/exchange/data/repository/ExchangeRepository;Lcom/project/exchange/domain/usecase/ConvertCurrencyUseCase;Landroid/content/SharedPreferences;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/project/exchange/ui/viewmodel/ExchangeUiState;", "refreshJob", "Lkotlinx/coroutines/Job;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addCurrency", "", "code", "", "fetchRates", "formatAmount", "amount", "", "currencyCode", "loadSavedCurrencies", "onAmountChanged", "onCurrencyChanged", "index", "", "newCurrencyCode", "refreshRates", "removeCurrency", "saveCurrencies", "currencies", "", "startAutoRefresh", "Companion", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ExchangeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.project.exchange.data.repository.ExchangeRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.project.exchange.domain.usecase.ConvertCurrencyUseCase convertCurrencyUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.project.exchange.ui.viewmodel.ExchangeUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.project.exchange.ui.viewmodel.ExchangeUiState> uiState = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job refreshJob;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_SELECTED_CURRENCIES = "selected_currencies";
    @org.jetbrains.annotations.NotNull()
    public static final com.project.exchange.ui.viewmodel.ExchangeViewModel.Companion Companion = null;
    
    @javax.inject.Inject()
    public ExchangeViewModel(@org.jetbrains.annotations.NotNull()
    com.project.exchange.data.repository.ExchangeRepository repository, @org.jetbrains.annotations.NotNull()
    com.project.exchange.domain.usecase.ConvertCurrencyUseCase convertCurrencyUseCase, @org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences prefs) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.project.exchange.ui.viewmodel.ExchangeUiState> getUiState() {
        return null;
    }
    
    private final void loadSavedCurrencies() {
    }
    
    private final void saveCurrencies(java.util.List<java.lang.String> currencies) {
    }
    
    public final void onAmountChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String currencyCode, @org.jetbrains.annotations.NotNull()
    java.lang.String amount) {
    }
    
    public final void onCurrencyChanged(int index, @org.jetbrains.annotations.NotNull()
    java.lang.String newCurrencyCode) {
    }
    
    public final void addCurrency(@org.jetbrains.annotations.NotNull()
    java.lang.String code) {
    }
    
    public final void removeCurrency(int index) {
    }
    
    public final void refreshRates() {
    }
    
    private final void fetchRates() {
    }
    
    private final void startAutoRefresh() {
    }
    
    private final java.lang.String formatAmount(double amount, java.lang.String currencyCode) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/project/exchange/ui/viewmodel/ExchangeViewModel$Companion;", "", "()V", "KEY_SELECTED_CURRENCIES", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}