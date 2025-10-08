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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\tH\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J=\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010 \u001a\u00020!H\u00d6\u0001J\t\u0010\"\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006#"}, d2 = {"Lcom/example/healthconnectdemo/healthconnect/BodyTemperature;", "", "recordId", "", "temperature", "Landroidx/health/connect/client/units/Temperature;", "time", "Ljava/time/Instant;", "zoneOffset", "Ljava/time/ZoneOffset;", "userInfo", "Lcom/example/healthconnectdemo/model/UserInfo;", "(Ljava/lang/String;Landroidx/health/connect/client/units/Temperature;Ljava/time/Instant;Ljava/time/ZoneOffset;Lcom/example/healthconnectdemo/model/UserInfo;)V", "getRecordId", "()Ljava/lang/String;", "getTemperature", "()Landroidx/health/connect/client/units/Temperature;", "getTime", "()Ljava/time/Instant;", "getUserInfo", "()Lcom/example/healthconnectdemo/model/UserInfo;", "getZoneOffset", "()Ljava/time/ZoneOffset;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_devDebug"})
public final class BodyTemperature {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String recordId = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.health.connect.client.units.Temperature temperature = null;
    @org.jetbrains.annotations.NotNull()
    private final java.time.Instant time = null;
    @org.jetbrains.annotations.NotNull()
    private final java.time.ZoneOffset zoneOffset = null;
    @org.jetbrains.annotations.Nullable()
    private final com.example.healthconnectdemo.model.UserInfo userInfo = null;
    
    public BodyTemperature(@org.jetbrains.annotations.NotNull()
    java.lang.String recordId, @org.jetbrains.annotations.NotNull()
    androidx.health.connect.client.units.Temperature temperature, @org.jetbrains.annotations.NotNull()
    java.time.Instant time, @org.jetbrains.annotations.NotNull()
    java.time.ZoneOffset zoneOffset, @org.jetbrains.annotations.Nullable()
    com.example.healthconnectdemo.model.UserInfo userInfo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRecordId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.health.connect.client.units.Temperature getTemperature() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.Instant getTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.ZoneOffset getZoneOffset() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.healthconnectdemo.model.UserInfo getUserInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.health.connect.client.units.Temperature component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.Instant component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.ZoneOffset component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.healthconnectdemo.model.UserInfo component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.healthconnectdemo.healthconnect.BodyTemperature copy(@org.jetbrains.annotations.NotNull()
    java.lang.String recordId, @org.jetbrains.annotations.NotNull()
    androidx.health.connect.client.units.Temperature temperature, @org.jetbrains.annotations.NotNull()
    java.time.Instant time, @org.jetbrains.annotations.NotNull()
    java.time.ZoneOffset zoneOffset, @org.jetbrains.annotations.Nullable()
    com.example.healthconnectdemo.model.UserInfo userInfo) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}