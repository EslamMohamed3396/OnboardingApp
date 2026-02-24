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
public final class SaveUserNameAndCountryUseCase_Factory implements Factory<SaveUserNameAndCountryUseCase> {
  private final Provider<UserRepository> userRepositoryProvider;

  public SaveUserNameAndCountryUseCase_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public SaveUserNameAndCountryUseCase get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static SaveUserNameAndCountryUseCase_Factory create(
      Provider<UserRepository> userRepositoryProvider) {
    return new SaveUserNameAndCountryUseCase_Factory(userRepositoryProvider);
  }

  public static SaveUserNameAndCountryUseCase newInstance(UserRepository userRepository) {
    return new SaveUserNameAndCountryUseCase(userRepository);
  }
}
