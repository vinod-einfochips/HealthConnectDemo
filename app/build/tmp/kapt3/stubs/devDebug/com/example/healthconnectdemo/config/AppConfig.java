package com.example.healthconnectdemo.config;

import com.example.healthconnectdemo.BuildConfig;

/**
 * Application configuration based on build variant
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0019\u001a\u00020\u0004J\u0006\u0010\u001a\u001a\u00020\u0004J\u0006\u0010\u001b\u001a\u00020\u001cR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\fX\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\rR\u0014\u0010\u000e\u001a\u00020\fX\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0014\u0010\u000f\u001a\u00020\fX\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\fX\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0014\u0010\u0011\u001a\u00020\fX\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0014\u0010\u0012\u001a\u00020\fX\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0014\u0010\u0013\u001a\u00020\u0014X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006\u00a8\u0006\u001d"}, d2 = {"Lcom/example/healthconnectdemo/config/AppConfig;", "", "()V", "applicationId", "", "getApplicationId", "()Ljava/lang/String;", "baseUrl", "getBaseUrl", "environment", "getEnvironment", "isCrashReportingEnabled", "", "()Z", "isDebugMode", "isDevelopment", "isLoggingEnabled", "isProduction", "isQA", "versionCode", "", "getVersionCode", "()I", "versionName", "getVersionName", "getEnvironmentDisplayName", "getVersionInfo", "printConfig", "", "app_devDebug"})
public final class AppConfig {
    
    /**
     * Base URL for API calls
     */
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String baseUrl = "https://dev-api.healthconnect.com";
    
    /**
     * Current environment (DEV, QA, PRODUCTION)
     */
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String environment = "DEV";
    
    /**
     * Enable/disable logging
     */
    private static final boolean isLoggingEnabled = com.example.healthconnectdemo.BuildConfig.ENABLE_LOGGING;
    
    /**
     * Enable/disable crash reporting
     */
    private static final boolean isCrashReportingEnabled = com.example.healthconnectdemo.BuildConfig.ENABLE_CRASH_REPORTING;
    
    /**
     * Debug mode flag
     */
    private static final boolean isDebugMode = com.example.healthconnectdemo.BuildConfig.DEBUG_MODE;
    
    /**
     * Check if running in development environment
     */
    private static final boolean isDevelopment = false;
    
    /**
     * Check if running in QA environment
     */
    private static final boolean isQA = false;
    
    /**
     * Check if running in production environment
     */
    private static final boolean isProduction = false;
    
    /**
     * Application version name
     */
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String versionName = "1.0-dev";
    
    /**
     * Application version code
     */
    private static final int versionCode = com.example.healthconnectdemo.BuildConfig.VERSION_CODE;
    
    /**
     * Application ID
     */
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String applicationId = "com.example.healthconnectdemo.dev";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.healthconnectdemo.config.AppConfig INSTANCE = null;
    
    private AppConfig() {
        super();
    }
    
    /**
     * Base URL for API calls
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBaseUrl() {
        return null;
    }
    
    /**
     * Current environment (DEV, QA, PRODUCTION)
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEnvironment() {
        return null;
    }
    
    /**
     * Enable/disable logging
     */
    public final boolean isLoggingEnabled() {
        return false;
    }
    
    /**
     * Enable/disable crash reporting
     */
    public final boolean isCrashReportingEnabled() {
        return false;
    }
    
    /**
     * Debug mode flag
     */
    public final boolean isDebugMode() {
        return false;
    }
    
    /**
     * Check if running in development environment
     */
    public final boolean isDevelopment() {
        return false;
    }
    
    /**
     * Check if running in QA environment
     */
    public final boolean isQA() {
        return false;
    }
    
    /**
     * Check if running in production environment
     */
    public final boolean isProduction() {
        return false;
    }
    
    /**
     * Application version name
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVersionName() {
        return null;
    }
    
    /**
     * Application version code
     */
    public final int getVersionCode() {
        return 0;
    }
    
    /**
     * Application ID
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getApplicationId() {
        return null;
    }
    
    /**
     * Get environment display name
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEnvironmentDisplayName() {
        return null;
    }
    
    /**
     * Get full version info
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVersionInfo() {
        return null;
    }
    
    /**
     * Print configuration (for debugging)
     */
    public final void printConfig() {
    }
}