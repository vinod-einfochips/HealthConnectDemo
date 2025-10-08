package com.example.healthconnectdemo.model

import java.time.Instant
import java.time.ZoneOffset

data class TemperatureReading(
    val recordId: String,
    val timestamp: Instant,
    val temperatureCelsius: Double,
    val temperatureFahrenheit: Double,
    val date: String,
    val time: String,
    val zoneOffset: ZoneOffset,
) {
    fun getFormattedCelsius(): String = String.format("%.1f°C", temperatureCelsius)

    fun getFormattedFahrenheit(): String = String.format("%.1f°F", temperatureFahrenheit)

    fun getFormattedDateTime(): String = "$date at $time"
}
