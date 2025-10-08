package com.example.healthconnectdemo.viewmodel;

import com.example.healthconnectdemo.healthconnect.HealthConnectManager;
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
public final class MainViewModel_Factory implements Factory<MainViewModel> {
  private final Provider<HealthConnectManager> healthConnectManagerProvider;

  public MainViewModel_Factory(Provider<HealthConnectManager> healthConnectManagerProvider) {
    this.healthConnectManagerProvider = healthConnectManagerProvider;
  }

  @Override
  public MainViewModel get() {
    return newInstance(healthConnectManagerProvider.get());
  }

  public static MainViewModel_Factory create(
      Provider<HealthConnectManager> healthConnectManagerProvider) {
    return new MainViewModel_Factory(healthConnectManagerProvider);
  }

  public static MainViewModel newInstance(HealthConnectManager healthConnectManager) {
    return new MainViewModel(healthConnectManager);
  }
}
