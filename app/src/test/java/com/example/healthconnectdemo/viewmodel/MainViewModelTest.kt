package com.example.healthconnectdemo.viewmodel

import com.example.healthconnectdemo.healthconnect.BodyTemperature
import com.example.healthconnectdemo.healthconnect.HealthConnectManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.Instant

@ExperimentalCoroutinesApi
@ExtendWith(MockitoExtension::class)
@DisplayName("MainViewModel Temperature Tests")
class MainViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var healthConnectManager: HealthConnectManager

    private lateinit var viewModel: MainViewModel

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(healthConnectManager)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // ========== POSITIVE TEST CASES ==========

    @Nested
    @DisplayName("Positive Temperature Validation Tests")
    inner class PositiveValidationTests {
        @ParameterizedTest(name = "Valid temperature: {0}°C")
        @ValueSource(strings = ["20.0", "25.5", "30.0", "36.5", "37.0", "40.0", "45.0"])
        fun `should validate temperature within valid range`(temperature: String) {
            // When
            val result = viewModel.validateTemperatureInput(temperature)

            // Then
            assertTrue(result, "Temperature $temperature should be valid")
        }

        @Test
        @DisplayName("Should validate minimum boundary temperature 20°C")
        fun `should validate minimum boundary temperature`() {
            // When
            val result = viewModel.validateTemperatureInput("20.0")

            // Then
            assertTrue(result)
        }

        @Test
        @DisplayName("Should validate maximum boundary temperature 45°C")
        fun `should validate maximum boundary temperature`() {
            // When
            val result = viewModel.validateTemperatureInput("45.0")

            // Then
            assertTrue(result)
        }

        @Test
        @DisplayName("Should validate normal body temperature")
        fun `should validate normal body temperature`() {
            // Given
            val normalTemperatures = listOf("36.5", "37.0", "37.5")

            // When & Then
            normalTemperatures.forEach { temp ->
                assertTrue(
                    viewModel.validateTemperatureInput(temp),
                    "Normal body temperature $temp should be valid",
                )
            }
        }

        @Test
        @DisplayName("Should validate temperature with decimal precision")
        fun `should validate temperature with decimal precision`() {
            // Given
            val preciseTemperatures = listOf("36.1", "36.25", "36.789")

            // When & Then
            preciseTemperatures.forEach { temp ->
                assertTrue(
                    viewModel.validateTemperatureInput(temp),
                    "Temperature $temp with decimal precision should be valid",
                )
            }
        }
    }

    @Nested
    @DisplayName("Positive Temperature Recording Tests")
    inner class PositiveRecordingTests {
        @Test
        @DisplayName("Should successfully record valid temperature")
        fun `should successfully record valid temperature`() =
            runTest {
                // Given
                val validTemperature = 37.5

                // When
                viewModel.recordTemperature(validTemperature)
                advanceUntilIdle()

                // Then
                verify(healthConnectManager).writeBodyTemperature(
                    temperature = validTemperature,
                    time = any(),
                )
                val result = viewModel.temperatureRecorded.value
                assertNotNull(result)
                assertTrue(result!!.isSuccess)
                assertEquals("Temperature recorded: $validTemperature°C", result.getOrNull())
            }

        @Test
        @DisplayName("Should successfully record temperature")
        fun `should successfully record temperature`() =
            runTest {
                // Given
                val temperature = 38.0

                // When
                viewModel.recordTemperature(temperature)
                advanceUntilIdle()

                // Then
                verify(healthConnectManager).writeBodyTemperature(
                    temperature = temperature,
                    time = any(),
                )
                val result = viewModel.temperatureRecorded.value
                assertNotNull(result)
                assertTrue(result!!.isSuccess)
            }

        @ParameterizedTest(name = "Should record boundary temperature: {0}°C")
        @ValueSource(doubles = [20.0, 45.0])
        fun `should record boundary temperatures`(temperature: Double) =
            runTest {
                // When
                viewModel.recordTemperature(temperature)
                advanceUntilIdle()

                // Then
                val result = viewModel.temperatureRecorded.value
                assertNotNull(result)
                assertTrue(result!!.isSuccess)
            }

        @Test
        @DisplayName("Should record multiple temperatures in sequence")
        fun `should record multiple temperatures in sequence`() =
            runTest {
                // Given
                val temperatures = listOf(36.5, 37.0, 37.5, 38.0)

                // When & Then
                temperatures.forEach { temp ->
                    viewModel.recordTemperature(temp)
                    advanceUntilIdle()

                    val result = viewModel.temperatureRecorded.value
                    assertNotNull(result)
                    assertTrue(result!!.isSuccess)
                }
            }
    }

    @Nested
    @DisplayName("Positive Permission Tests")
    inner class PositivePermissionTests {
        @Test
        @DisplayName("Should update status when permissions are granted")
        fun `should update status when permissions are granted`() =
            runTest {
                // Given
                whenever(healthConnectManager.hasAllPermissions()).thenReturn(true)

                // When
                viewModel.checkPermissions()
                advanceUntilIdle()

                // Then
                assertEquals(true, viewModel.permissionStatus.value)
            }

        @Test
        @DisplayName("Should load temperature history successfully")
        fun `should load temperature history successfully`() =
            runTest {
                // Given
                val start = Instant.now().minusSeconds(3600)
                val end = Instant.now()
                val mockTemperatures =
                    listOf(
                        createMockBodyTemperature("1", 36.5),
                        createMockBodyTemperature("2", 37.0),
                        createMockBodyTemperature("3", 37.5),
                    )

                whenever(healthConnectManager.readBodyTemperatures(start, end))
                    .thenReturn(flowOf(*mockTemperatures.toTypedArray()))

                // When
                viewModel.loadTemperatureHistory(start, end)
                advanceUntilIdle()

                // Then
                val history = viewModel.temperatureHistory.value
                assertNotNull(history)
                assertEquals(3, history!!.size)
            }
    }

    // ========== NEGATIVE TEST CASES ==========

    @Nested
    @DisplayName("Negative Temperature Validation Tests")
    inner class NegativeValidationTests {
        @ParameterizedTest(name = "Invalid temperature: {0}")
        @ValueSource(strings = ["", "abc", "36.5.5", "not a number", "temp", "##", "null"])
        fun `should reject invalid temperature input`(invalidInput: String) {
            // When
            val result = viewModel.validateTemperatureInput(invalidInput)

            // Then
            assertFalse(result, "Input '$invalidInput' should be invalid")
        }

        @ParameterizedTest(name = "Temperature below minimum: {0}°C")
        @ValueSource(strings = ["19.9", "15.0", "10.0", "0.0", "-5.0"])
        fun `should reject temperature below minimum`(temperature: String) {
            // When
            val result = viewModel.validateTemperatureInput(temperature)

            // Then
            assertFalse(result, "Temperature $temperature should be below minimum")
        }

        @ParameterizedTest(name = "Temperature above maximum: {0}°C")
        @ValueSource(strings = ["45.1", "50.0", "60.0", "100.0"])
        fun `should reject temperature above maximum`(temperature: String) {
            // When
            val result = viewModel.validateTemperatureInput(temperature)

            // Then
            assertFalse(result, "Temperature $temperature should be above maximum")
        }

        @Test
        @DisplayName("Should reject null or empty input")
        fun `should reject null or empty input`() {
            // When & Then
            assertFalse(viewModel.validateTemperatureInput(""))
            assertFalse(viewModel.validateTemperatureInput("   "))
        }

        @Test
        @DisplayName("Should reject special characters")
        fun `should reject special characters`() {
            // Given
            val invalidInputs = listOf("36@5", "37#0", "38$5", "39%0")

            // When & Then
            invalidInputs.forEach { input ->
                assertFalse(
                    viewModel.validateTemperatureInput(input),
                    "Input '$input' with special characters should be invalid",
                )
            }
        }
    }

    @Nested
    @DisplayName("Negative Temperature Recording Tests")
    inner class NegativeRecordingTests {
        @ParameterizedTest(name = "Should fail for temperature: {0}°C")
        @ValueSource(doubles = [19.9, 15.0, 10.0, 0.0])
        fun `should fail to record temperature below minimum`(temperature: Double) =
            runTest {
                // Given

                // When
                viewModel.recordTemperature(temperature)
                advanceUntilIdle()

                // Then
                verify(healthConnectManager, never()).writeBodyTemperature(any(), any())
                val result = viewModel.temperatureRecorded.value
                assertNotNull(result)
                assertTrue(result!!.isFailure)
                assertEquals(
                    "Please enter a valid temperature between 20°C and 45°C",
                    result.exceptionOrNull()?.message,
                )
            }

        @ParameterizedTest(name = "Should fail for temperature: {0}°C")
        @ValueSource(doubles = [45.1, 50.0, 60.0, 100.0])
        fun `should fail to record temperature above maximum`(temperature: Double) =
            runTest {
                // Given

                // When
                viewModel.recordTemperature(temperature)
                advanceUntilIdle()

                // Then
                verify(healthConnectManager, never()).writeBodyTemperature(any(), any())
                val result = viewModel.temperatureRecorded.value
                assertNotNull(result)
                assertTrue(result!!.isFailure)
            }

        @Test
        @DisplayName("Should handle exception from HealthConnectManager")
        fun `should handle exception from HealthConnectManager`() =
            runTest {
                // Given
                val validTemperature = 37.0
                val errorMessage = "Failed to write to Health Connect"
                whenever(healthConnectManager.writeBodyTemperature(any(), any()))
                    .thenThrow(RuntimeException(errorMessage))

                // When
                viewModel.recordTemperature(validTemperature)
                advanceUntilIdle()

                // Then
                val result = viewModel.temperatureRecorded.value
                assertNotNull(result)
                assertTrue(result!!.isFailure)
                assertNotNull(viewModel.errorMessage.value)
            }

        @Test
        @DisplayName("Should handle network timeout exception")
        fun `should handle network timeout exception`() =
            runTest {
                // Given
                val temperature = 37.5
                whenever(healthConnectManager.writeBodyTemperature(any(), any()))
                    .thenThrow(RuntimeException("Network timeout"))

                // When
                viewModel.recordTemperature(temperature)
                advanceUntilIdle()

                // Then
                val result = viewModel.temperatureRecorded.value
                assertNotNull(result)
                assertTrue(result!!.isFailure)
            }
    }

    @Nested
    @DisplayName("Negative Permission Tests")
    inner class NegativePermissionTests {
        @Test
        @DisplayName("Should update status when permissions are denied")
        fun `should update status when permissions are denied`() =
            runTest {
                // Given
                whenever(healthConnectManager.hasAllPermissions()).thenReturn(false)

                // When
                viewModel.checkPermissions()
                advanceUntilIdle()

                // Then
                assertEquals(false, viewModel.permissionStatus.value)
            }

        @Test
        @DisplayName("Should handle exception during permission check")
        fun `should handle exception during permission check`() =
            runTest {
                // Given
                val errorMessage = "Permission check failed"
                whenever(healthConnectManager.hasAllPermissions())
                    .thenThrow(RuntimeException(errorMessage))

                // When
                viewModel.checkPermissions()
                advanceUntilIdle()

                // Then
                assertNotNull(viewModel.errorMessage.value)
                assertTrue(viewModel.errorMessage.value!!.contains("Error checking permissions"))
            }

        @Test
        @DisplayName("Should handle empty temperature history")
        fun `should handle empty temperature history`() =
            runTest {
                // Given
                val start = Instant.now().minusSeconds(3600)
                val end = Instant.now()

                whenever(healthConnectManager.readBodyTemperatures(start, end))
                    .thenReturn(flowOf())

                // When
                viewModel.loadTemperatureHistory(start, end)
                advanceUntilIdle()

                // Then
                val history = viewModel.temperatureHistory.value
                assertNotNull(history)
                assertTrue(history!!.isEmpty())
            }

        @Test
        @DisplayName("Should handle exception when loading history")
        fun `should handle exception when loading history`() =
            runTest {
                // Given
                val start = Instant.now().minusSeconds(3600)
                val end = Instant.now()
                val errorMessage = "Failed to load history"

                whenever(healthConnectManager.readBodyTemperatures(start, end))
                    .thenThrow(RuntimeException(errorMessage))

                // When
                viewModel.loadTemperatureHistory(start, end)
                advanceUntilIdle()

                // Then
                assertNotNull(viewModel.errorMessage.value)
                assertTrue(viewModel.errorMessage.value!!.contains("Error loading temperature history"))
            }
    }

    // ========== EDGE CASE TESTS ==========

    @Nested
    @DisplayName("Edge Case Tests")
    inner class EdgeCaseTests {
        @Test
        @DisplayName("Should handle exactly minimum temperature")
        fun `should handle exactly minimum temperature`() {
            // When
            val result = viewModel.validateTemperatureInput("20.0")

            // Then
            assertTrue(result)
        }

        @Test
        @DisplayName("Should handle exactly maximum temperature")
        fun `should handle exactly maximum temperature`() {
            // When
            val result = viewModel.validateTemperatureInput("45.0")

            // Then
            assertTrue(result)
        }

        @Test
        @DisplayName("Should handle temperature just below minimum")
        fun `should handle temperature just below minimum`() {
            // When
            val result = viewModel.validateTemperatureInput("19.999999")

            // Then
            assertFalse(result)
        }

        @Test
        @DisplayName("Should handle temperature just above maximum")
        fun `should handle temperature just above maximum`() {
            // When
            val result = viewModel.validateTemperatureInput("45.000001")

            // Then
            assertFalse(result)
        }

        @Test
        @DisplayName("Should handle very long decimal precision")
        fun `should handle very long decimal precision`() {
            // When
            val result = viewModel.validateTemperatureInput("37.123456789012345")

            // Then
            assertTrue(result)
        }

        @Test
        @DisplayName("Should handle leading and trailing spaces")
        fun `should handle leading and trailing spaces`() {
            // When & Then
            assertFalse(viewModel.validateTemperatureInput("  37.5  "))
        }
    }

    // ========== Helper Methods ==========

    private fun createMockBodyTemperature(
        id: String,
        tempCelsius: Double,
    ): BodyTemperature {
        return BodyTemperature(
            recordId = id,
            temperature = androidx.health.connect.client.units.Temperature.celsius(tempCelsius),
            time = Instant.now(),
            zoneOffset = java.time.ZoneOffset.UTC,
        )
    }
}
