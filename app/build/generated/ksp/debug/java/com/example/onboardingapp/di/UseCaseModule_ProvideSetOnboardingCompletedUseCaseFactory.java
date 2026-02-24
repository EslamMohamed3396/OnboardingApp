package com.example.onboardingapp.di;

import com.example.onboardingapp.domain.repository.UserRepository;
import com.example.onboardingapp.domain.usecase.SetOnboardingCompletedUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class UseCaseModule_ProvideSetOnboardingCompletedUseCaseFactory implements Factory<SetOnboardingCompletedUseCase> {
  private final Provider<UserRepository> userRepositoryProvider;

  public UseCaseModule_ProvideSetOnboardingCompletedUseCaseFactory(
      Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public SetOnboardingCompletedUseCase get() {
    return provideSetOnboardingCompletedUseCase(userRepositoryProvider.get());
  }

  public static UseCaseModule_ProvideSetOnboardingCompletedUseCaseFactory create(
      Provider<UserRepository> userRepositoryProvider) {
    return new UseCaseModule_ProvideSetOnboardingCompletedUseCaseFactory(userRepositoryProvider);
  }

  public static SetOnboardingCompletedUseCase provideSetOnboardingCompletedUseCase(
      UserRepository userRepository) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideSetOnboardingCompletedUseCase(userRepository));
  }
}
