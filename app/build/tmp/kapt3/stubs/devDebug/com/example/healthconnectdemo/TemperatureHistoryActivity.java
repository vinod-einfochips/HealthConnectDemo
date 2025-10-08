package com.example.healthconnectdemo;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.healthconnectdemo.adapter.TemperatureHistoryAdapter;
import com.example.healthconnectdemo.databinding.ActivityTemperatureHistoryBinding;
import com.example.healthconnectdemo.model.TemperatureReading;
import com.example.healthconnectdemo.viewmodel.HistoryViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\u0012\u0010\u0010\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u000eH\u0002J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n\u00a8\u0006\u001b"}, d2 = {"Lcom/example/healthconnectdemo/TemperatureHistoryActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/example/healthconnectdemo/adapter/TemperatureHistoryAdapter;", "binding", "Lcom/example/healthconnectdemo/databinding/ActivityTemperatureHistoryBinding;", "viewModel", "Lcom/example/healthconnectdemo/viewmodel/HistoryViewModel;", "getViewModel", "()Lcom/example/healthconnectdemo/viewmodel/HistoryViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "loadHistory", "", "observeViewModel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "setupRecyclerView", "showDeleteConfirmationDialog", "reading", "Lcom/example/healthconnectdemo/model/TemperatureReading;", "app_devDebug"})
public final class TemperatureHistoryActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.healthconnectdemo.databinding.ActivityTemperatureHistoryBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.example.healthconnectdemo.adapter.TemperatureHistoryAdapter adapter;
    
    public TemperatureHistoryActivity() {
        super();
    }
    
    private final com.example.healthconnectdemo.viewmodel.HistoryViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void showDeleteConfirmationDialog(com.example.healthconnectdemo.model.TemperatureReading reading) {
    }
    
    private final void observeViewModel() {
    }
    
    private final void loadHistory() {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
}