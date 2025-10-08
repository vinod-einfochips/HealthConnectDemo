package com.example.healthconnectdemo;

import androidx.health.connect.client.units.Temperature;
import com.example.healthconnectdemo.healthconnect.BodyTemperature;
import com.example.healthconnectdemo.model.TemperatureReading;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Utility functions for testing temperature-related functionality
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u001d\u001eB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J.\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eJ*\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00042\b\b\u0002\u0010\u0015\u001a\u00020\u0004J.\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0017\u001a\u00020\f2\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0005\u001a\u00020\u0004\u00a8\u0006\u001f"}, d2 = {"Lcom/example/healthconnectdemo/TestUtils;", "", "()V", "celsiusToFahrenheit", "", "celsius", "createMockBodyTemperature", "Lcom/example/healthconnectdemo/healthconnect/BodyTemperature;", "recordId", "", "temperatureCelsius", "time", "Ljava/time/Instant;", "zoneOffset", "Ljava/time/ZoneOffset;", "createMockTemperatureList", "", "Lcom/example/healthconnectdemo/model/TemperatureReading;", "count", "", "startTemp", "increment", "createMockTemperatureReading", "timestamp", "fahrenheitToCelsius", "fahrenheit", "isNormalBodyTemperature", "", "isValidTemperature", "TestTemperatures", "TestTimes", "app_devDebugUnitTest"})
public final class TestUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.healthconnectdemo.TestUtils INSTANCE = null;
    
    private TestUtils() {
        super();
    }
    
    /**
     * Creates a mock BodyTemperature for testing
     */
    @org.jetbrains.annotations.NotNull()
    public final com.example.healthconnectdemo.healthconnect.BodyTemperature createMockBodyTemperature(@org.jetbrains.annotations.NotNull()
    java.lang.String recordId, double temperatureCelsius, @org.jetbrains.annotations.NotNull()
    java.time.Instant time, @org.jetbrains.annotations.NotNull()
    java.time.ZoneOffset zoneOffset) {
        return null;
    }
    
    /**
     * Creates a mock TemperatureReading for testing
     */
    @org.jetbrains.annotations.NotNull()
    public final com.example.healthconnectdemo.model.TemperatureReading createMockTemperatureReading(@org.jetbrains.annotations.NotNull()
    java.lang.String recordId, @org.jetbrains.annotations.NotNull()
    java.time.Instant timestamp, double temperatureCelsius, @org.jetbrains.annotations.NotNull()
    java.time.ZoneOffset zoneOffset) {
        return null;
    }
    
    /**
     * Converts Celsius to Fahrenheit
     */
    public final double celsiusToFahrenheit(double celsius) {
        return 0.0;
    }
    
    /**
     * Converts Fahrenheit to Celsius
     */
    public final double fahrenheitToCelsius(double fahrenheit) {
        return 0.0;
    }
    
    /**
     * Checks if temperature is in valid range (20°C - 45°C)
     */
    public final boolean isValidTemperature(double celsius) {
        return false;
    }
    
    /**
     * Checks if temperature is in normal body temperature range (36.5°C - 37.5°C)
     */
    public final boolean isNormalBodyTemperature(double celsius) {
        return false;
    }
    
    /**
     * Creates a list of mock temperature readings for testing
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.healthconnectdemo.model.TemperatureReading> createMockTemperatureList(int count, double startTemp, double increment) {
        return null;
    }
    
    /**
     * Common test temperature values
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/example/healthconnectdemo/TestUtils$TestTemperatures;", "", "()V", "ABOVE_MAX", "", "BELOW_MIN", "FEVER", "HIGH_FEVER", "MAX_VALID", "MIN_VALID", "NORMAL_HIGH", "NORMAL_LOW", "NORMAL_MID", "app_devDebugUnitTest"})
    public static final class TestTemperatures {
        public static final double MIN_VALID = 20.0;
        public static final double MAX_VALID = 45.0;
        public static final double BELOW_MIN = 19.9;
        public static final double ABOVE_MAX = 45.1;
        public static final double NORMAL_LOW = 36.5;
        public static final double NORMAL_MID = 37.0;
        public static final double NORMAL_HIGH = 37.5;
        public static final double FEVER = 38.5;
        public static final double HIGH_FEVER = 40.0;
        @org.jetbrains.annotations.NotNull()
        public static final com.example.healthconnectdemo.TestUtils.TestTemperatures INSTANCE = null;
        
        private TestTemperatures() {
            super();
        }
    }
    
    /**
     * Common test time values
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006\u00a8\u0006\r"}, d2 = {"Lcom/example/healthconnectdemo/TestUtils$TestTimes;", "", "()V", "NOW", "Ljava/time/Instant;", "getNOW", "()Ljava/time/Instant;", "ONE_DAY_AGO", "getONE_DAY_AGO", "ONE_HOUR_AGO", "getONE_HOUR_AGO", "ONE_WEEK_AGO", "getONE_WEEK_AGO", "app_devDebugUnitTest"})
    public static final class TestTimes {
        @org.jetbrains.annotations.NotNull()
        private static final java.time.Instant NOW = null;
        @org.jetbrains.annotations.NotNull()
        private static final java.time.Instant ONE_HOUR_AGO = null;
        @org.jetbrains.annotations.NotNull()
        private static final java.time.Instant ONE_DAY_AGO = null;
        @org.jetbrains.annotations.NotNull()
        private static final java.time.Instant ONE_WEEK_AGO = null;
        @org.jetbrains.annotations.NotNull()
        public static final com.example.healthconnectdemo.TestUtils.TestTimes INSTANCE = null;
        
        private TestTimes() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.time.Instant getNOW() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.time.Instant getONE_HOUR_AGO() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.time.Instant getONE_DAY_AGO() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.time.Instant getONE_WEEK_AGO() {
            return null;
        }
    }
}