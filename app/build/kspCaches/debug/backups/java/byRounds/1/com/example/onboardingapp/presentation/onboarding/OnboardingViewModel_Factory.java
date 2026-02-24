package com.example.onboardingapp.presentation.onboarding;

import com.example.onboardingapp.domain.usecase.SetOnboardingCompletedUseCase;
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
public final class OnboardingViewModel_Factory implements Factory<OnboardingViewModel> {
  private final Provider<SetOnboardingCompletedUseCase> setOnboardingCompletedUseCaseProvider;

  public OnboardingViewModel_Factory(
      Provider<SetOnboardingCompletedUseCase> setOnboardingCompletedUseCaseProvider) {
    this.setOnboardingCompletedUseCaseProvider = setOnboardingCompletedUseCaseProvider;
  }

  @Override
  public OnboardingViewModel get() {
    return newInstance(setOnboardingCompletedUseCaseProvider.get());
  }

  public static OnboardingViewModel_Factory create(
      Provider<SetOnboardingCompletedUseCase> setOnboardingCompletedUseCaseProvider) {
    return new OnboardingViewModel_Factory(setOnboardingCompletedUseCaseProvider);
  }

  public static OnboardingViewModel newInstance(
      SetOnboardingCompletedUseCase setOnboardingCompletedUseCase) {
    return new OnboardingViewModel(setOnboardingCompletedUseCase);
  }
}
