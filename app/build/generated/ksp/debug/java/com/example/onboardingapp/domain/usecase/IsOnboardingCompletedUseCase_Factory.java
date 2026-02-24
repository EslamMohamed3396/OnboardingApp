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
public final class IsOnboardingCompletedUseCase_Factory implements Factory<IsOnboardingCompletedUseCase> {
  private final Provider<UserRepository> userRepositoryProvider;

  public IsOnboardingCompletedUseCase_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public IsOnboardingCompletedUseCase get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static IsOnboardingCompletedUseCase_Factory create(
      Provider<UserRepository> userRepositoryProvider) {
    return new IsOnboardingCompletedUseCase_Factory(userRepositoryProvider);
  }

  public static IsOnboardingCompletedUseCase newInstance(UserRepository userRepository) {
    return new IsOnboardingCompletedUseCase(userRepository);
  }
}
