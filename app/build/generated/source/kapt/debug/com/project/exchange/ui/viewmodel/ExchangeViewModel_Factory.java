package com.project.exchange.ui.viewmodel;

import android.content.SharedPreferences;
import com.project.exchange.data.repository.ExchangeRepository;
import com.project.exchange.domain.usecase.ConvertCurrencyUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class ExchangeViewModel_Factory implements Factory<ExchangeViewModel> {
  private final Provider<ExchangeRepository> repositoryProvider;

  private final Provider<ConvertCurrencyUseCase> convertCurrencyUseCaseProvider;

  private final Provider<SharedPreferences> prefsProvider;

  public ExchangeViewModel_Factory(Provider<ExchangeRepository> repositoryProvider,
      Provider<ConvertCurrencyUseCase> convertCurrencyUseCaseProvider,
      Provider<SharedPreferences> prefsProvider) {
    this.repositoryProvider = repositoryProvider;
    this.convertCurrencyUseCaseProvider = convertCurrencyUseCaseProvider;
    this.prefsProvider = prefsProvider;
  }

  @Override
  public ExchangeViewModel get() {
    return newInstance(repositoryProvider.get(), convertCurrencyUseCaseProvider.get(), prefsProvider.get());
  }

  public static ExchangeViewModel_Factory create(Provider<ExchangeRepository> repositoryProvider,
      Provider<ConvertCurrencyUseCase> convertCurrencyUseCaseProvider,
      Provider<SharedPreferences> prefsProvider) {
    return new ExchangeViewModel_Factory(repositoryProvider, convertCurrencyUseCaseProvider, prefsProvider);
  }

  public static ExchangeViewModel newInstance(ExchangeRepository repository,
      ConvertCurrencyUseCase convertCurrencyUseCase, SharedPreferences prefs) {
    return new ExchangeViewModel(repository, convertCurrencyUseCase, prefs);
  }
}
