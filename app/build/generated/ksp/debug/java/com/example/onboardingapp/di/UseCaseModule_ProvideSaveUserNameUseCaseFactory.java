package com.example.onboardingapp.di;

import com.example.onboardingapp.domain.repository.UserRepository;
import com.example.onboardingapp.domain.usecase.SaveUserNameUseCase;
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
public final class UseCaseModule_ProvideSaveUserNameUseCaseFactory implements Factory<SaveUserNameUseCase> {
  private final Provider<UserRepository> userRepositoryProvider;

  public UseCaseModule_ProvideSaveUserNameUseCaseFactory(
      Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public SaveUserNameUseCase get() {
    return provideSaveUserNameUseCase(userRepositoryProvider.get());
  }

  public static UseCaseModule_ProvideSaveUserNameUseCaseFactory create(
      Provider<UserRepository> userRepositoryProvider) {
    return new UseCaseModule_ProvideSaveUserNameUseCaseFactory(userRepositoryProvider);
  }

  public static SaveUserNameUseCase provideSaveUserNameUseCase(UserRepository userRepository) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideSaveUserNameUseCase(userRepository));
  }
}
