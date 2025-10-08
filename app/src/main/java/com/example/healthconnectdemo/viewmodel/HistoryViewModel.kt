package com.example.healthconnectdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthconnectdemo.healthconnect.BodyTemperature
import com.example.healthconnectdemo.healthconnect.HealthConnectManager
import com.example.healthconnectdemo.model.TemperatureReading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel
    @Inject
    constructor(
        private val healthConnectManager: HealthConnectManager,
    ) : ViewModel() {
        private val _temperatureHistory = MutableLiveData<List<TemperatureReading>>()
        val temperatureHistory: LiveData<List<TemperatureReading>> = _temperatureHistory

        private val _errorMessage = MutableLiveData<String>()
        val errorMessage: LiveData<String> = _errorMessage

        private val _isLoading = MutableLiveData<Boolean>()
        val isLoading: LiveData<Boolean> = _isLoading

        fun loadTemperatureHistory(
            start: Instant,
            end: Instant,
        ) {
            viewModelScope.launch {
                try {
                    _isLoading.value = true
                    _errorMessage.value = ""

                    val readings = mutableListOf<TemperatureReading>()

                    healthConnectManager.readBodyTemperatures(start, end).collect { bodyTemp ->
                        readings.add(convertToTemperatureReading(bodyTemp))
                    }

                    // Sort by time descending (newest first)
                    val sortedReadings = readings.sortedByDescending { it.timestamp }
                    _temperatureHistory.value = sortedReadings
                } catch (e: Exception) {
                    _errorMessage.value = "Error loading temperature history: ${e.message}"
                    _temperatureHistory.value = emptyList()
                } finally {
                    _isLoading.value = false
                }
            }
        }

        fun deleteTemperatureReading(recordId: String) {
            viewModelScope.launch {
                try {
                    _isLoading.value = true
                    healthConnectManager.deleteBodyTemperature(recordId)

                    // Remove from current list
                    val currentList = _temperatureHistory.value ?: emptyList()
                    _temperatureHistory.value = currentList.filter { it.recordId != recordId }
                } catch (e: Exception) {
                    _errorMessage.value = "Error deleting temperature: ${e.message}"
                } finally {
                    _isLoading.value = false
                }
            }
        }

        private fun convertToTemperatureReading(bodyTemp: BodyTemperature): TemperatureReading {
            val celsius = bodyTemp.temperature.inCelsius
            val fahrenheit = celsiusToFahrenheit(celsius)

            val dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
            val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")

            val zonedDateTime = bodyTemp.time.atZone(bodyTemp.zoneOffset)

            return TemperatureReading(
                recordId = bodyTemp.recordId,
                timestamp = bodyTemp.time,
                temperatureCelsius = celsius,
                temperatureFahrenheit = fahrenheit,
                date = zonedDateTime.format(dateFormatter),
                time = zonedDateTime.format(timeFormatter),
                zoneOffset = bodyTemp.zoneOffset,
            )
        }

        private fun celsiusToFahrenheit(celsius: Double): Double {
            return (celsius * 9.0 / 5.0) + 32.0
        }
    }
