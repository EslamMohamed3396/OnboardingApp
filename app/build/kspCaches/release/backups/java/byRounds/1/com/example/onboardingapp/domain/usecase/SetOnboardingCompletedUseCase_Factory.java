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
public final class SetOnboardingCompletedUseCase_Factory implements Factory<SetOnboardingCompletedUseCase> {
  private final Provider<UserRepository> userRepositoryProvider;

  public SetOnboardingCompletedUseCase_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public SetOnboardingCompletedUseCase get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static SetOnboardingCompletedUseCase_Factory create(
      Provider<UserRepository> userRepositoryProvider) {
    return new SetOnboardingCompletedUseCase_Factory(userRepositoryProvider);
  }

  public static SetOnboardingCompletedUseCase newInstance(UserRepository userRepository) {
    return new SetOnboardingCompletedUseCase(userRepository);
  }
}
