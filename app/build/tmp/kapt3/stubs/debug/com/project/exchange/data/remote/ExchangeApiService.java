package com.project.exchange.data.remote;

import retrofit2.http.GET;
import retrofit2.http.Query;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/project/exchange/data/remote/ExchangeApiService;", "", "getLatestRates", "Lcom/project/exchange/data/remote/ExchangeApiResponse;", "from", "", "to", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ExchangeApiService {
    
    @retrofit2.http.GET(value = "latest")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestRates(@retrofit2.http.Query(value = "from")
    @org.jetbrains.annotations.NotNull()
    java.lang.String from, @retrofit2.http.Query(value = "to")
    @org.jetbrains.annotations.NotNull()
    java.lang.String to, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.project.exchange.data.remote.ExchangeApiResponse> $completion);
}