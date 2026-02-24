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
public final class SaveUserNameUseCase_Factory implements Factory<SaveUserNameUseCase> {
  private final Provider<UserRepository> userRepositoryProvider;

  public SaveUserNameUseCase_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public SaveUserNameUseCase get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static SaveUserNameUseCase_Factory create(
      Provider<UserRepository> userRepositoryProvider) {
    return new SaveUserNameUseCase_Factory(userRepositoryProvider);
  }

  public static SaveUserNameUseCase newInstance(UserRepository userRepository) {
    return new SaveUserNameUseCase(userRepository);
  }
}
