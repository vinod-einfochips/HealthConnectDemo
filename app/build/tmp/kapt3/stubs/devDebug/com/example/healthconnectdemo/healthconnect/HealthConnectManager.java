package com.example.healthconnectdemo.healthconnect;

import android.content.Context;
import androidx.health.connect.client.HealthConnectClient;
import androidx.health.connect.client.permission.HealthPermission;
import androidx.health.connect.client.records.BodyTemperatureRecord;
import androidx.health.connect.client.records.metadata.Metadata;
import androidx.health.connect.client.time.TimeRangeFilter;
import androidx.health.connect.client.units.Temperature;
import com.example.healthconnectdemo.model.UserInfo;
import com.example.healthconnectdemo.model.UserType;
import kotlinx.coroutines.flow.Flow;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\tH\u0002J\u000e\u0010\u0017\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u0018J$\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u0010\u001fJ,\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\u001d2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0086@\u00a2\u0006\u0002\u0010$R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006%"}, d2 = {"Lcom/example/healthconnectdemo/healthconnect/HealthConnectManager;", "", "healthConnectClient", "Landroidx/health/connect/client/HealthConnectClient;", "context", "Landroid/content/Context;", "(Landroidx/health/connect/client/HealthConnectClient;Landroid/content/Context;)V", "permissions", "", "", "getPermissions", "()Ljava/util/Set;", "checkHealthConnectIsAvailable", "", "createClientRecordId", "userInfo", "Lcom/example/healthconnectdemo/model/UserInfo;", "deleteBodyTemperature", "", "recordId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "extractUserInfoFromMetadata", "clientRecordId", "hasAllPermissions", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readBodyTemperatures", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/healthconnectdemo/healthconnect/BodyTemperature;", "start", "Ljava/time/Instant;", "end", "(Ljava/time/Instant;Ljava/time/Instant;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeBodyTemperature", "temperature", "", "time", "(DLjava/time/Instant;Lcom/example/healthconnectdemo/model/UserInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_devDebug"})
public final class HealthConnectManager {
    @org.jetbrains.annotations.NotNull()
    private final androidx.health.connect.client.HealthConnectClient healthConnectClient = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.String> permissions = null;
    
    @javax.inject.Inject()
    public HealthConnectManager(@org.jetbrains.annotations.NotNull()
    androidx.health.connect.client.HealthConnectClient healthConnectClient, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> getPermissions() {
        return null;
    }
    
    public final boolean checkHealthConnectIsAvailable() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object hasAllPermissions(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object readBodyTemperatures(@org.jetbrains.annotations.NotNull()
    java.time.Instant start, @org.jetbrains.annotations.NotNull()
    java.time.Instant end, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<com.example.healthconnectdemo.healthconnect.BodyTemperature>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object writeBodyTemperature(double temperature, @org.jetbrains.annotations.NotNull()
    java.time.Instant time, @org.jetbrains.annotations.Nullable()
    com.example.healthconnectdemo.model.UserInfo userInfo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Creates a client record ID that encodes user information
     * Format: userName|userType|userId
     */
    private final java.lang.String createClientRecordId(com.example.healthconnectdemo.model.UserInfo userInfo) {
        return null;
    }
    
    /**
     * Extracts user information from client record ID
     */
    private final com.example.healthconnectdemo.model.UserInfo extractUserInfoFromMetadata(java.lang.String clientRecordId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteBodyTemperature(@org.jetbrains.annotations.NotNull()
    java.lang.String recordId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}