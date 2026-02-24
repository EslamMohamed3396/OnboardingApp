package com.example.onboardingapp.di;

import com.example.onboardingapp.data.local.UserDataStore;
import com.example.onboardingapp.domain.repository.UserRepository;
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
public final class RepositoryModule_ProvideUserRepositoryFactory implements Factory<UserRepository> {
  private final Provider<UserDataStore> userDataStoreProvider;

  public RepositoryModule_ProvideUserRepositoryFactory(
      Provider<UserDataStore> userDataStoreProvider) {
    this.userDataStoreProvider = userDataStoreProvider;
  }

  @Override
  public UserRepository get() {
    return provideUserRepository(userDataStoreProvider.get());
  }

  public static RepositoryModule_ProvideUserRepositoryFactory create(
      Provider<UserDataStore> userDataStoreProvider) {
    return new RepositoryModule_ProvideUserRepositoryFactory(userDataStoreProvider);
  }

  public static UserRepository provideUserRepository(UserDataStore userDataStore) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideUserRepository(userDataStore));
  }
}
