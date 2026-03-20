package com.project.exchange.domain.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class ConvertCurrencyUseCase_Factory implements Factory<ConvertCurrencyUseCase> {
  @Override
  public ConvertCurrencyUseCase get() {
    return newInstance();
  }

  public static ConvertCurrencyUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ConvertCurrencyUseCase newInstance() {
    return new ConvertCurrencyUseCase();
  }

  private static final class InstanceHolder {
    private static final ConvertCurrencyUseCase_Factory INSTANCE = new ConvertCurrencyUseCase_Factory();
  }
}
