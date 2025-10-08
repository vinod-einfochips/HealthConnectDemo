package com.example.healthconnectdemo

import androidx.health.connect.client.units.Temperature
import com.example.healthconnectdemo.healthconnect.BodyTemperature
import com.example.healthconnectdemo.model.TemperatureReading
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * Utility functions for testing temperature-related functionality
 */
object TestUtils {
    /**
     * Creates a mock BodyTemperature for testing
     */
    fun createMockBodyTemperature(
        recordId: String = "test-id-${System.currentTimeMillis()}",
        temperatureCelsius: Double = 37.0,
        time: Instant = Instant.now(),
        zoneOffset: ZoneOffset = ZoneOffset.UTC,
    ): BodyTemperature {
        return BodyTemperature(
            recordId = recordId,
            temperature = Temperature.celsius(temperatureCelsius),
            time = time,
            zoneOffset = zoneOffset,
        )
    }

    /**
     * Creates a mock TemperatureReading for testing
     */
    fun createMockTemperatureReading(
        recordId: String = "test-id-${System.currentTimeMillis()}",
        timestamp: Instant = Instant.now(),
        temperatureCelsius: Double = 37.0,
        zoneOffset: ZoneOffset = ZoneOffset.UTC,
    ): TemperatureReading {
        val temperatureFahrenheit = celsiusToFahrenheit(temperatureCelsius)
        val zoneId = ZoneId.ofOffset("UTC", zoneOffset)

        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(zoneId)
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(zoneId)

        return TemperatureReading(
            recordId = recordId,
            timestamp = timestamp,
            temperatureCelsius = temperatureCelsius,
            temperatureFahrenheit = temperatureFahrenheit,
            date = dateFormatter.format(timestamp),
            time = timeFormatter.format(timestamp),
            zoneOffset = zoneOffset,
        )
    }

    /**
     * Converts Celsius to Fahrenheit
     */
    fun celsiusToFahrenheit(celsius: Double): Double {
        return celsius * 9.0 / 5.0 + 32.0
    }

    /**
     * Converts Fahrenheit to Celsius
     */
    fun fahrenheitToCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32.0) * 5.0 / 9.0
    }

    /**
     * Checks if temperature is in valid range (20°C - 45°C)
     */
    fun isValidTemperature(celsius: Double): Boolean {
        return celsius in 20.0..45.0
    }

    /**
     * Checks if temperature is in normal body temperature range (36.5°C - 37.5°C)
     */
    fun isNormalBodyTemperature(celsius: Double): Boolean {
        return celsius in 36.5..37.5
    }

    /**
     * Creates a list of mock temperature readings for testing
     */
    fun createMockTemperatureList(
        count: Int = 5,
        startTemp: Double = 36.5,
        increment: Double = 0.2,
    ): List<TemperatureReading> {
        return (0 until count).map { index ->
            createMockTemperatureReading(
                recordId = "test-id-$index",
                temperatureCelsius = startTemp + (increment * index),
                timestamp = Instant.now().minusSeconds((count - index) * 3600L),
            )
        }
    }

    /**
     * Common test temperature values
     */
    object TestTemperatures {
        const val MIN_VALID = 20.0
        const val MAX_VALID = 45.0
        const val BELOW_MIN = 19.9
        const val ABOVE_MAX = 45.1
        const val NORMAL_LOW = 36.5
        const val NORMAL_MID = 37.0
        const val NORMAL_HIGH = 37.5
        const val FEVER = 38.5
        const val HIGH_FEVER = 40.0
    }

    /**
     * Common test time values
     */
    object TestTimes {
        val NOW: Instant = Instant.now()
        val ONE_HOUR_AGO: Instant = NOW.minusSeconds(3600)
        val ONE_DAY_AGO: Instant = NOW.minusSeconds(86400)
        val ONE_WEEK_AGO: Instant = NOW.minusSeconds(604800)
    }
}
