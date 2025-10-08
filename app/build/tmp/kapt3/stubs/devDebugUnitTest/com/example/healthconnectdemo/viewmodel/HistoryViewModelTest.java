package com.example.healthconnectdemo.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.example.healthconnectdemo.healthconnect.BodyTemperature;
import com.example.healthconnectdemo.healthconnect.HealthConnectManager;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@org.junit.jupiter.api.extension.ExtendWith(value = {org.mockito.junit.jupiter.MockitoExtension.class})
@org.junit.jupiter.api.DisplayName(value = "HistoryViewModel Tests")
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0004\u0019\u001a\u001b\u001cB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J \u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0007J\b\u0010\u0018\u001a\u00020\u0017H\u0007R\u0012\u0010\u0003\u001a\u00020\u00048\u0002@\u0002X\u0083.\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001a\u00020\u00068G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/example/healthconnectdemo/viewmodel/HistoryViewModelTest;", "", "()V", "healthConnectManager", "Lcom/example/healthconnectdemo/healthconnect/HealthConnectManager;", "instantTaskExecutorRule", "Landroidx/arch/core/executor/testing/InstantTaskExecutorRule;", "getInstantTaskExecutorRule", "()Landroidx/arch/core/executor/testing/InstantTaskExecutorRule;", "testDispatcher", "Lkotlinx/coroutines/test/TestDispatcher;", "viewModel", "Lcom/example/healthconnectdemo/viewmodel/HistoryViewModel;", "createMockBodyTemperature", "Lcom/example/healthconnectdemo/healthconnect/BodyTemperature;", "id", "", "tempCelsius", "", "createMockBodyTemperatureWithTime", "time", "Ljava/time/Instant;", "setup", "", "tearDown", "ConversionTests", "DeleteTemperatureTests", "LoadHistoryTests", "LoadingStateTests", "app_devDebugUnitTest"})
@kotlinx.coroutines.ExperimentalCoroutinesApi()
public final class HistoryViewModelTest {
    @org.jetbrains.annotations.NotNull()
    private final androidx.arch.core.executor.testing.InstantTaskExecutorRule instantTaskExecutorRule = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.test.TestDispatcher testDispatcher = null;
    @org.mockito.Mock()
    private com.example.healthconnectdemo.healthconnect.HealthConnectManager healthConnectManager;
    private com.example.healthconnectdemo.viewmodel.HistoryViewModel viewModel;
    
    public HistoryViewModelTest() {
        super();
    }
    
    @org.junit.Rule()
    @org.jetbrains.annotations.NotNull()
    public final androidx.arch.core.executor.testing.InstantTaskExecutorRule getInstantTaskExecutorRule() {
        return null;
    }
    
    @org.junit.jupiter.api.BeforeEach()
    public final void setup() {
    }
    
    @org.junit.jupiter.api.AfterEach()
    public final void tearDown() {
    }
    
    private final com.example.healthconnectdemo.healthconnect.BodyTemperature createMockBodyTemperature(java.lang.String id, double tempCelsius) {
        return null;
    }
    
    private final com.example.healthconnectdemo.healthconnect.BodyTemperature createMockBodyTemperatureWithTime(java.lang.String id, double tempCelsius, java.time.Instant time) {
        return null;
    }
    
    @org.junit.jupiter.api.Nested()
    @org.junit.jupiter.api.DisplayName(value = "Temperature Conversion Tests")
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005H\u0007J\f\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005H\u0007\u00a8\u0006\u0007"}, d2 = {"Lcom/example/healthconnectdemo/viewmodel/HistoryViewModelTest$ConversionTests;", "", "(Lcom/example/healthconnectdemo/viewmodel/HistoryViewModelTest;)V", "should convert Celsius to Fahrenheit correctly", "", "Lkotlinx/coroutines/test/TestResult;", "should format temperature values correctly", "app_devDebugUnitTest"})
    public final class ConversionTests {
        
        public ConversionTests() {
            super();
        }
    }
    
    @org.junit.jupiter.api.Nested()
    @org.junit.jupiter.api.DisplayName(value = "Delete Temperature Tests")
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005H\u0007J\f\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005H\u0007J\f\u0010\u0007\u001a\u00060\u0004j\u0002`\u0005H\u0007\u00a8\u0006\b"}, d2 = {"Lcom/example/healthconnectdemo/viewmodel/HistoryViewModelTest$DeleteTemperatureTests;", "", "(Lcom/example/healthconnectdemo/viewmodel/HistoryViewModelTest;)V", "should delete temperature reading successfully", "", "Lkotlinx/coroutines/test/TestResult;", "should handle exception when deleting temperature", "should remove deleted item from list", "app_devDebugUnitTest"})
    public final class DeleteTemperatureTests {
        
        public DeleteTemperatureTests() {
            super();
        }
    }
    
    @org.junit.jupiter.api.Nested()
    @org.junit.jupiter.api.DisplayName(value = "Load Temperature History Tests")
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005H\u0007J\f\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005H\u0007J\f\u0010\u0007\u001a\u00060\u0004j\u0002`\u0005H\u0007J\f\u0010\b\u001a\u00060\u0004j\u0002`\u0005H\u0007\u00a8\u0006\t"}, d2 = {"Lcom/example/healthconnectdemo/viewmodel/HistoryViewModelTest$LoadHistoryTests;", "", "(Lcom/example/healthconnectdemo/viewmodel/HistoryViewModelTest;)V", "should handle empty temperature history", "", "Lkotlinx/coroutines/test/TestResult;", "should handle exception when loading history", "should load temperature history successfully", "should sort temperature history by newest first", "app_devDebugUnitTest"})
    public final class LoadHistoryTests {
        
        public LoadHistoryTests() {
            super();
        }
    }
    
    @org.junit.jupiter.api.Nested()
    @org.junit.jupiter.api.DisplayName(value = "Loading State Tests")
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005H\u0007J\f\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005H\u0007\u00a8\u0006\u0007"}, d2 = {"Lcom/example/healthconnectdemo/viewmodel/HistoryViewModelTest$LoadingStateTests;", "", "(Lcom/example/healthconnectdemo/viewmodel/HistoryViewModelTest;)V", "should clear loading state after error", "", "Lkotlinx/coroutines/test/TestResult;", "should set loading state during operation", "app_devDebugUnitTest"})
    public final class LoadingStateTests {
        
        public LoadingStateTests() {
            super();
        }
    }
}