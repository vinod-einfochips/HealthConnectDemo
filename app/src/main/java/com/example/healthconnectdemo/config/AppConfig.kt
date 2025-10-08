package com.example.healthconnectdemo.config

import com.example.healthconnectdemo.BuildConfig

/**
 * Application configuration based on build variant
 */
object AppConfig {
    /**
     * Base URL for API calls
     */
    val baseUrl: String = BuildConfig.BASE_URL

    /**
     * Current environment (DEV, QA, PRODUCTION)
     */
    val environment: String = BuildConfig.ENVIRONMENT

    /**
     * Enable/disable logging
     */
    val isLoggingEnabled: Boolean = BuildConfig.ENABLE_LOGGING

    /**
     * Enable/disable crash reporting
     */
    val isCrashReportingEnabled: Boolean = BuildConfig.ENABLE_CRASH_REPORTING

    /**
     * Debug mode flag
     */
    val isDebugMode: Boolean = BuildConfig.DEBUG_MODE

    /**
     * Check if running in development environment
     */
    val isDevelopment: Boolean = environment == "DEV"

    /**
     * Check if running in QA environment
     */
    val isQA: Boolean = environment == "QA"

    /**
     * Check if running in production environment
     */
    val isProduction: Boolean = environment == "PRODUCTION"

    /**
     * Application version name
     */
    val versionName: String = BuildConfig.VERSION_NAME

    /**
     * Application version code
     */
    val versionCode: Int = BuildConfig.VERSION_CODE

    /**
     * Application ID
     */
    val applicationId: String = BuildConfig.APPLICATION_ID

    /**
     * Get environment display name
     */
    fun getEnvironmentDisplayName(): String {
        return when (environment) {
            "DEV" -> "Development"
            "QA" -> "Quality Assurance"
            "PRODUCTION" -> "Production"
            else -> "Unknown"
        }
    }

    /**
     * Get full version info
     */
    fun getVersionInfo(): String {
        return "v$versionName ($versionCode) - ${getEnvironmentDisplayName()}"
    }

    /**
     * Print configuration (for debugging)
     */
    fun printConfig() {
        if (isLoggingEnabled) {
            println("=== App Configuration ===")
            println("Environment: $environment")
            println("Base URL: $baseUrl")
            println("Version: $versionName ($versionCode)")
            println("Logging: $isLoggingEnabled")
            println("Crash Reporting: $isCrashReportingEnabled")
            println("Debug Mode: $isDebugMode")
            println("========================")
        }
    }
}
