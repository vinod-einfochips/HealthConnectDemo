package com.example.healthconnectdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthconnectdemo.healthconnect.BodyTemperature
import com.example.healthconnectdemo.healthconnect.HealthConnectManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val healthConnectManager: HealthConnectManager,
    ) : ViewModel() {
        private val _permissionStatus = MutableLiveData<Boolean>()
        val permissionStatus: LiveData<Boolean> = _permissionStatus

        private val _temperatureRecorded = MutableLiveData<Result<String>>()
        val temperatureRecorded: LiveData<Result<String>> = _temperatureRecorded

        private val _temperatureHistory = MutableLiveData<List<BodyTemperature>>()
        val temperatureHistory: LiveData<List<BodyTemperature>> = _temperatureHistory

        private val _errorMessage = MutableLiveData<String>()
        val errorMessage: LiveData<String> = _errorMessage

        val permissions = healthConnectManager.permissions

        fun checkPermissions() {
            viewModelScope.launch {
                try {
                    val hasPermissions = healthConnectManager.hasAllPermissions()
                    _permissionStatus.value = hasPermissions
                } catch (e: Exception) {
                    _errorMessage.value = "Error checking permissions: ${e.message}"
                }
            }
        }

        fun recordTemperature(temperature: Double) {
            viewModelScope.launch {
                try {
                    if (temperature < 20.0 || temperature > 45.0) {
                        _temperatureRecorded.value =
                            Result.failure(
                                IllegalArgumentException("Please enter a valid temperature between 20°C and 45°C"),
                            )
                        return@launch
                    }

                    healthConnectManager.writeBodyTemperature(
                        temperature = temperature,
                        time = Instant.now(),
                    )

                    _temperatureRecorded.value = Result.success("Temperature recorded: $temperature°C")
                } catch (e: Exception) {
                    _temperatureRecorded.value = Result.failure(e)
                    _errorMessage.value = "Error recording temperature: ${e.message}"
                }
            }
        }

        fun loadTemperatureHistory(
            start: Instant,
            end: Instant,
        ) {
            viewModelScope.launch {
                try {
                    val temperatures = mutableListOf<BodyTemperature>()
                    healthConnectManager.readBodyTemperatures(start, end).collect { temp ->
                        temperatures.add(temp)
                    }
                    _temperatureHistory.value = temperatures
                } catch (e: Exception) {
                    _errorMessage.value = "Error loading temperature history: ${e.message}"
                }
            }
        }

        fun validateTemperatureInput(input: String): Boolean {
            return try {
                val temp = input.toDouble()
                temp in 20.0..45.0
            } catch (e: NumberFormatException) {
                false
            }
        }
    }
