package com.example.healthconnectdemo.di;

import android.content.Context;
import androidx.health.connect.client.HealthConnectClient;
import com.example.healthconnectdemo.healthconnect.HealthConnectManager;
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
public final class AppModule_ProvideHealthConnectManagerFactory implements Factory<HealthConnectManager> {
  private final Provider<HealthConnectClient> healthConnectClientProvider;

  private final Provider<Context> contextProvider;

  public AppModule_ProvideHealthConnectManagerFactory(
      Provider<HealthConnectClient> healthConnectClientProvider,
      Provider<Context> contextProvider) {
    this.healthConnectClientProvider = healthConnectClientProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public HealthConnectManager get() {
    return provideHealthConnectManager(healthConnectClientProvider.get(), contextProvider.get());
  }

  public static AppModule_ProvideHealthConnectManagerFactory create(
      Provider<HealthConnectClient> healthConnectClientProvider,
      Provider<Context> contextProvider) {
    return new AppModule_ProvideHealthConnectManagerFactory(healthConnectClientProvider, contextProvider);
  }

  public static HealthConnectManager provideHealthConnectManager(
      HealthConnectClient healthConnectClient, Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideHealthConnectManager(healthConnectClient, context));
  }
}
