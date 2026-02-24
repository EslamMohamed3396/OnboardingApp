package com.example.onboardingapp.presentation;

import com.example.onboardingapp.domain.usecase.IsOnboardingCompletedUseCase;
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
public final class StartupViewModel_Factory implements Factory<StartupViewModel> {
  private final Provider<IsOnboardingCompletedUseCase> isOnboardingCompletedUseCaseProvider;

  public StartupViewModel_Factory(
      Provider<IsOnboardingCompletedUseCase> isOnboardingCompletedUseCaseProvider) {
    this.isOnboardingCompletedUseCaseProvider = isOnboardingCompletedUseCaseProvider;
  }

  @Override
  public StartupViewModel get() {
    return newInstance(isOnboardingCompletedUseCaseProvider.get());
  }

  public static StartupViewModel_Factory create(
      Provider<IsOnboardingCompletedUseCase> isOnboardingCompletedUseCaseProvider) {
    return new StartupViewModel_Factory(isOnboardingCompletedUseCaseProvider);
  }

  public static StartupViewModel newInstance(
      IsOnboardingCompletedUseCase isOnboardingCompletedUseCase) {
    return new StartupViewModel(isOnboardingCompletedUseCase);
  }
}
