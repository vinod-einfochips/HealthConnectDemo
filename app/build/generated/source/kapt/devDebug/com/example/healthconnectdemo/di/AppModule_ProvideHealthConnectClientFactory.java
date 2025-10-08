package com.example.healthconnectdemo.di;

import android.content.Context;
import androidx.health.connect.client.HealthConnectClient;
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
public final class AppModule_ProvideHealthConnectClientFactory implements Factory<HealthConnectClient> {
  private final Provider<Context> contextProvider;

  public AppModule_ProvideHealthConnectClientFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public HealthConnectClient get() {
    return provideHealthConnectClient(contextProvider.get());
  }

  public static AppModule_ProvideHealthConnectClientFactory create(
      Provider<Context> contextProvider) {
    return new AppModule_ProvideHealthConnectClientFactory(contextProvider);
  }

  public static HealthConnectClient provideHealthConnectClient(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideHealthConnectClient(context));
  }
}
