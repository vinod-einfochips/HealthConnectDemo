package com.example.healthconnectdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.health.connect.client.HealthConnectClient;
import androidx.health.connect.client.PermissionController;
import com.example.healthconnectdemo.databinding.ActivityMainBinding;
import com.example.healthconnectdemo.healthconnect.HealthConnectManager;
import com.example.healthconnectdemo.viewmodel.MainViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u0000 %2\u00020\u0001:\u0001%B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0012\u0010\u0017\u001a\u00020\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0016H\u0014J\b\u0010\u001b\u001a\u00020\u0016H\u0002J\b\u0010\u001c\u001a\u00020\u0016H\u0002J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0002J\b\u0010\u001f\u001a\u00020\u0016H\u0002J\b\u0010 \u001a\u00020\u0016H\u0002J\b\u0010!\u001a\u00020\u0016H\u0002J\b\u0010\"\u001a\u00020\u0016H\u0002J\b\u0010#\u001a\u00020\u0016H\u0002J\b\u0010$\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006&"}, d2 = {"Lcom/example/healthconnectdemo/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/healthconnectdemo/databinding/ActivityMainBinding;", "healthConnectManager", "Lcom/example/healthconnectdemo/healthconnect/HealthConnectManager;", "getHealthConnectManager", "()Lcom/example/healthconnectdemo/healthconnect/HealthConnectManager;", "setHealthConnectManager", "(Lcom/example/healthconnectdemo/healthconnect/HealthConnectManager;)V", "permissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "viewModel", "Lcom/example/healthconnectdemo/viewmodel/MainViewModel;", "getViewModel", "()Lcom/example/healthconnectdemo/viewmodel/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "observeViewModel", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "openHealthConnectApp", "openPlayStoreForHealthConnect", "requestHealthConnectPermissions", "requestPermissions", "setupUI", "showEnableHealthPermissionPopup", "showHealthConnectNotAvailableDialog", "showHealthConnectNotInstalledDialog", "showHealthConnectPermissionDialog", "showInstallHealthConnectPopup", "Companion", "app_devDebug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.healthconnectdemo.databinding.ActivityMainBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @javax.inject.Inject()
    public com.example.healthconnectdemo.healthconnect.HealthConnectManager healthConnectManager;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "MainActivity";
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.util.Set<java.lang.String>> permissionLauncher = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.healthconnectdemo.MainActivity.Companion Companion = null;
    
    public MainActivity() {
        super();
    }
    
    private final com.example.healthconnectdemo.viewmodel.MainViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.healthconnectdemo.healthconnect.HealthConnectManager getHealthConnectManager() {
        return null;
    }
    
    public final void setHealthConnectManager(@org.jetbrains.annotations.NotNull()
    com.example.healthconnectdemo.healthconnect.HealthConnectManager p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void requestHealthConnectPermissions() {
    }
    
    private final void requestPermissions() {
    }
    
    private final void showInstallHealthConnectPopup() {
    }
    
    private final void showEnableHealthPermissionPopup() {
    }
    
    private final void openPlayStoreForHealthConnect() {
    }
    
    private final void showHealthConnectPermissionDialog() {
    }
    
    private final void openHealthConnectApp() {
    }
    
    private final void showHealthConnectNotInstalledDialog() {
    }
    
    private final void setupUI() {
    }
    
    private final void observeViewModel() {
    }
    
    private final void showHealthConnectNotAvailableDialog() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/healthconnectdemo/MainActivity$Companion;", "", "()V", "TAG", "", "app_devDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}