package com.project.exchange.di;

import com.project.exchange.data.remote.ExchangeApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvideExchangeApiServiceFactory implements Factory<ExchangeApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideExchangeApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ExchangeApiService get() {
    return provideExchangeApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideExchangeApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideExchangeApiServiceFactory(retrofitProvider);
  }

  public static ExchangeApiService provideExchangeApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideExchangeApiService(retrofit));
  }
}
