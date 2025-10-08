package com.example.healthconnectdemo;

import com.example.healthconnectdemo.healthconnect.HealthConnectManager;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<HealthConnectManager> healthConnectManagerProvider;

  public MainActivity_MembersInjector(Provider<HealthConnectManager> healthConnectManagerProvider) {
    this.healthConnectManagerProvider = healthConnectManagerProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<HealthConnectManager> healthConnectManagerProvider) {
    return new MainActivity_MembersInjector(healthConnectManagerProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectHealthConnectManager(instance, healthConnectManagerProvider.get());
  }

  @InjectedFieldSignature("com.example.healthconnectdemo.MainActivity.healthConnectManager")
  public static void injectHealthConnectManager(MainActivity instance,
      HealthConnectManager healthConnectManager) {
    instance.healthConnectManager = healthConnectManager;
  }
}
