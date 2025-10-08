package com.example.healthconnectdemo.healthconnect;

import android.content.Context;
import androidx.health.connect.client.HealthConnectClient;
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
public final class HealthConnectManager_Factory implements Factory<HealthConnectManager> {
  private final Provider<HealthConnectClient> healthConnectClientProvider;

  private final Provider<Context> contextProvider;

  public HealthConnectManager_Factory(Provider<HealthConnectClient> healthConnectClientProvider,
      Provider<Context> contextProvider) {
    this.healthConnectClientProvider = healthConnectClientProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public HealthConnectManager get() {
    return newInstance(healthConnectClientProvider.get(), contextProvider.get());
  }

  public static HealthConnectManager_Factory create(
      Provider<HealthConnectClient> healthConnectClientProvider,
      Provider<Context> contextProvider) {
    return new HealthConnectManager_Factory(healthConnectClientProvider, contextProvider);
  }

  public static HealthConnectManager newInstance(HealthConnectClient healthConnectClient,
      Context context) {
    return new HealthConnectManager(healthConnectClient, context);
  }
}
