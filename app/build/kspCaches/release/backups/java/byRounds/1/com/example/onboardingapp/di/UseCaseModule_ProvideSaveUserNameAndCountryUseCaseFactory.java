package com.example.onboardingapp.di;

import com.example.onboardingapp.domain.repository.UserRepository;
import com.example.onboardingapp.domain.usecase.SaveUserNameAndCountryUseCase;
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
public final class UseCaseModule_ProvideSaveUserNameAndCountryUseCaseFactory implements Factory<SaveUserNameAndCountryUseCase> {
  private final Provider<UserRepository> userRepositoryProvider;

  public UseCaseModule_ProvideSaveUserNameAndCountryUseCaseFactory(
      Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public SaveUserNameAndCountryUseCase get() {
    return provideSaveUserNameAndCountryUseCase(userRepositoryProvider.get());
  }

  public static UseCaseModule_ProvideSaveUserNameAndCountryUseCaseFactory create(
      Provider<UserRepository> userRepositoryProvider) {
    return new UseCaseModule_ProvideSaveUserNameAndCountryUseCaseFactory(userRepositoryProvider);
  }

  public static SaveUserNameAndCountryUseCase provideSaveUserNameAndCountryUseCase(
      UserRepository userRepository) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideSaveUserNameAndCountryUseCase(userRepository));
  }
}
