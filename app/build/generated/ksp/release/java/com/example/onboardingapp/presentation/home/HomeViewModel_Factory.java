package com.example.onboardingapp.presentation.home;

import com.example.onboardingapp.domain.usecase.SaveUserCountryUseCase;
import com.example.onboardingapp.domain.usecase.SaveUserNameAndCountryUseCase;
import com.example.onboardingapp.domain.usecase.SaveUserNameUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<SaveUserNameUseCase> saveUserNameUseCaseProvider;

  private final Provider<SaveUserCountryUseCase> saveUserCountryUseCaseProvider;

  private final Provider<SaveUserNameAndCountryUseCase> saveUserNameAndCountryUseCaseProvider;

  public HomeViewModel_Factory(Provider<SaveUserNameUseCase> saveUserNameUseCaseProvider,
      Provider<SaveUserCountryUseCase> saveUserCountryUseCaseProvider,
      Provider<SaveUserNameAndCountryUseCase> saveUserNameAndCountryUseCaseProvider) {
    this.saveUserNameUseCaseProvider = saveUserNameUseCaseProvider;
    this.saveUserCountryUseCaseProvider = saveUserCountryUseCaseProvider;
    this.saveUserNameAndCountryUseCaseProvider = saveUserNameAndCountryUseCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(saveUserNameUseCaseProvider.get(), saveUserCountryUseCaseProvider.get(), saveUserNameAndCountryUseCaseProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<SaveUserNameUseCase> saveUserNameUseCaseProvider,
      Provider<SaveUserCountryUseCase> saveUserCountryUseCaseProvider,
      Provider<SaveUserNameAndCountryUseCase> saveUserNameAndCountryUseCaseProvider) {
    return new HomeViewModel_Factory(saveUserNameUseCaseProvider, saveUserCountryUseCaseProvider, saveUserNameAndCountryUseCaseProvider);
  }

  public static HomeViewModel newInstance(SaveUserNameUseCase saveUserNameUseCase,
      SaveUserCountryUseCase saveUserCountryUseCase,
      SaveUserNameAndCountryUseCase saveUserNameAndCountryUseCase) {
    return new HomeViewModel(saveUserNameUseCase, saveUserCountryUseCase, saveUserNameAndCountryUseCase);
  }
}
