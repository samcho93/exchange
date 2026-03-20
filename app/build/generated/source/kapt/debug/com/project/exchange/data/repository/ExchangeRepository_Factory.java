package com.project.exchange.data.repository;

import com.project.exchange.data.local.ExchangeRateDao;
import com.project.exchange.data.remote.ExchangeApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class ExchangeRepository_Factory implements Factory<ExchangeRepository> {
  private final Provider<ExchangeApiService> apiServiceProvider;

  private final Provider<ExchangeRateDao> daoProvider;

  public ExchangeRepository_Factory(Provider<ExchangeApiService> apiServiceProvider,
      Provider<ExchangeRateDao> daoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public ExchangeRepository get() {
    return newInstance(apiServiceProvider.get(), daoProvider.get());
  }

  public static ExchangeRepository_Factory create(Provider<ExchangeApiService> apiServiceProvider,
      Provider<ExchangeRateDao> daoProvider) {
    return new ExchangeRepository_Factory(apiServiceProvider, daoProvider);
  }

  public static ExchangeRepository newInstance(ExchangeApiService apiService, ExchangeRateDao dao) {
    return new ExchangeRepository(apiService, dao);
  }
}
