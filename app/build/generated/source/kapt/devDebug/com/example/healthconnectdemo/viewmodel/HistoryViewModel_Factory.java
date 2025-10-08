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
public final class HistoryViewModel_Factory implements Factory<HistoryViewModel> {
  private final Provider<HealthConnectManager> healthConnectManagerProvider;

  public HistoryViewModel_Factory(Provider<HealthConnectManager> healthConnectManagerProvider) {
    this.healthConnectManagerProvider = healthConnectManagerProvider;
  }

  @Override
  public HistoryViewModel get() {
    return newInstance(healthConnectManagerProvider.get());
  }

  public static HistoryViewModel_Factory create(
      Provider<HealthConnectManager> healthConnectManagerProvider) {
    return new HistoryViewModel_Factory(healthConnectManagerProvider);
  }

  public static HistoryViewModel newInstance(HealthConnectManager healthConnectManager) {
    return new HistoryViewModel(healthConnectManager);
  }
}
