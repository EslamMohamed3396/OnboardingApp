package com.example.onboardingapp.domain.usecase;

import com.example.onboardingapp.domain.repository.UserRepository;
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
public final class SaveUserCountryUseCase_Factory implements Factory<SaveUserCountryUseCase> {
  private final Provider<UserRepository> userRepositoryProvider;

  public SaveUserCountryUseCase_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public SaveUserCountryUseCase get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static SaveUserCountryUseCase_Factory create(
      Provider<UserRepository> userRepositoryProvider) {
    return new SaveUserCountryUseCase_Factory(userRepositoryProvider);
  }

  public static SaveUserCountryUseCase newInstance(UserRepository userRepository) {
    return new SaveUserCountryUseCase(userRepository);
  }
}
