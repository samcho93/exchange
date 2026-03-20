package com.project.exchange.di;

import com.project.exchange.data.local.ExchangeDatabase;
import com.project.exchange.data.local.ExchangeRateDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideExchangeRateDaoFactory implements Factory<ExchangeRateDao> {
  private final Provider<ExchangeDatabase> databaseProvider;

  public DatabaseModule_ProvideExchangeRateDaoFactory(Provider<ExchangeDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ExchangeRateDao get() {
    return provideExchangeRateDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideExchangeRateDaoFactory create(
      Provider<ExchangeDatabase> databaseProvider) {
    return new DatabaseModule_ProvideExchangeRateDaoFactory(databaseProvider);
  }

  public static ExchangeRateDao provideExchangeRateDao(ExchangeDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideExchangeRateDao(database));
  }
}
