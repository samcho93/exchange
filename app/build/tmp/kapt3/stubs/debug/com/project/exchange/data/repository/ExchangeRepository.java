package com.project.exchange.data.repository;

import com.project.exchange.data.local.ExchangeRateDao;
import com.project.exchange.data.local.ExchangeRateEntity;
import com.project.exchange.data.remote.ExchangeApiService;
import com.project.exchange.domain.model.ExchangeRate;
import kotlinx.coroutines.Dispatchers;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J2\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0010"}, d2 = {"Lcom/project/exchange/data/repository/ExchangeRepository;", "", "apiService", "Lcom/project/exchange/data/remote/ExchangeApiService;", "dao", "Lcom/project/exchange/data/local/ExchangeRateDao;", "(Lcom/project/exchange/data/remote/ExchangeApiService;Lcom/project/exchange/data/local/ExchangeRateDao;)V", "getExchangeRates", "Lkotlin/Result;", "Lcom/project/exchange/domain/model/ExchangeRate;", "base", "", "targets", "", "getExchangeRates-0E7RQCE", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ExchangeRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.project.exchange.data.remote.ExchangeApiService apiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.project.exchange.data.local.ExchangeRateDao dao = null;
    
    @javax.inject.Inject()
    public ExchangeRepository(@org.jetbrains.annotations.NotNull()
    com.project.exchange.data.remote.ExchangeApiService apiService, @org.jetbrains.annotations.NotNull()
    com.project.exchange.data.local.ExchangeRateDao dao) {
        super();
    }
}