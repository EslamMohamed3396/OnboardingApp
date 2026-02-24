package com.example.onboardingapp.di;

import android.content.Context;
import com.example.onboardingapp.data.local.UserDataStore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AppModule_ProvideUserDataStoreFactory implements Factory<UserDataStore> {
  private final Provider<Context> contextProvider;

  public AppModule_ProvideUserDataStoreFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public UserDataStore get() {
    return provideUserDataStore(contextProvider.get());
  }

  public static AppModule_ProvideUserDataStoreFactory create(Provider<Context> contextProvider) {
    return new AppModule_ProvideUserDataStoreFactory(contextProvider);
  }

  public static UserDataStore provideUserDataStore(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideUserDataStore(context));
  }
}
