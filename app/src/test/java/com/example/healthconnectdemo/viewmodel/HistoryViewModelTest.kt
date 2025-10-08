package com.example.healthconnectdemo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
import org.junit.Rule
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.Instant
import java.time.temporal.ChronoUnit

@ExperimentalCoroutinesApi
@ExtendWith(MockitoExtension::class)
@DisplayName("HistoryViewModel Tests")
class HistoryViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var healthConnectManager: HealthConnectManager

    private lateinit var viewModel: HistoryViewModel

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HistoryViewModel(healthConnectManager)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Nested
    @DisplayName("Load Temperature History Tests")
    inner class LoadHistoryTests {

        @Test
        @DisplayName("Should load temperature history successfully")
        fun `should load temperature history successfully`() = runTest {
            // Given
            val start = Instant.now().minus(30, ChronoUnit.DAYS)
            val end = Instant.now()
            val mockTemperatures = listOf(
                createMockBodyTemperature("1", 36.5),
                createMockBodyTemperature("2", 37.0),
                createMockBodyTemperature("3", 37.5)
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
            assertFalse(viewModel.isLoading.value!!)
        }

        @Test
        @DisplayName("Should handle empty temperature history")
        fun `should handle empty temperature history`() = runTest {
            // Given
            val start = Instant.now().minus(30, ChronoUnit.DAYS)
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
        fun `should handle exception when loading history`() = runTest {
            // Given
            val start = Instant.now().minus(30, ChronoUnit.DAYS)
            val end = Instant.now()
            val errorMessage = "Failed to load history"

            whenever(healthConnectManager.readBodyTemperatures(start, end))
                .thenThrow(RuntimeException(errorMessage))

            // When
            viewModel.loadTemperatureHistory(start, end)
            advanceUntilIdle()

            // Then
            val error = viewModel.errorMessage.value
            assertNotNull(error)
            assertTrue(error!!.contains("Error loading temperature history"))
        }

        @Test
        @DisplayName("Should sort temperature history by newest first")
        fun `should sort temperature history by newest first`() = runTest {
            // Given
            val start = Instant.now().minus(30, ChronoUnit.DAYS)
            val end = Instant.now()
            val oldTime = Instant.now().minus(2, ChronoUnit.DAYS)
            val recentTime = Instant.now().minus(1, ChronoUnit.DAYS)
            val newestTime = Instant.now()

            val mockTemperatures = listOf(
                createMockBodyTemperatureWithTime("1", 36.5, oldTime),
                createMockBodyTemperatureWithTime("2", 37.0, newestTime),
                createMockBodyTemperatureWithTime("3", 37.5, recentTime)
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
            // Verify newest first
            assertTrue(history[0].timestamp.isAfter(history[1].timestamp))
            assertTrue(history[1].timestamp.isAfter(history[2].timestamp))
        }
    }

    @Nested
    @DisplayName("Delete Temperature Tests")
    inner class DeleteTemperatureTests {

        @Test
        @DisplayName("Should delete temperature reading successfully")
        fun `should delete temperature reading successfully`() = runTest {
            // Given
            val recordId = "test-record-id"
            val start = Instant.now().minus(30, ChronoUnit.DAYS)
            val end = Instant.now()
            val mockTemperatures = listOf(
                createMockBodyTemperature(recordId, 36.5),
                createMockBodyTemperature("2", 37.0)
            )

            whenever(healthConnectManager.readBodyTemperatures(start, end))
                .thenReturn(flowOf(*mockTemperatures.toTypedArray()))

            // Load initial data
            viewModel.loadTemperatureHistory(start, end)
            advanceUntilIdle()

            // When
            viewModel.deleteTemperatureReading(recordId)
            advanceUntilIdle()

            // Then
            verify(healthConnectManager).deleteBodyTemperature(recordId)
            val history = viewModel.temperatureHistory.value
            assertNotNull(history)
            assertEquals(1, history!!.size)
            assertFalse(history.any { it.recordId == recordId })
        }

        @Test
        @DisplayName("Should handle exception when deleting temperature")
        fun `should handle exception when deleting temperature`() = runTest {
            // Given
            val recordId = "test-record-id"
            val errorMessage = "Failed to delete"

            whenever(healthConnectManager.deleteBodyTemperature(recordId))
                .thenThrow(RuntimeException(errorMessage))

            // When
            viewModel.deleteTemperatureReading(recordId)
            advanceUntilIdle()

            // Then
            val error = viewModel.errorMessage.value
            assertNotNull(error)
            assertTrue(error!!.contains("Error deleting temperature"))
        }

        @Test
        @DisplayName("Should remove deleted item from list")
        fun `should remove deleted item from list`() = runTest {
            // Given
            val recordId = "delete-me"
            val start = Instant.now().minus(30, ChronoUnit.DAYS)
            val end = Instant.now()
            val mockTemperatures = listOf(
                createMockBodyTemperature("1", 36.5),
                createMockBodyTemperature(recordId, 37.0),
                createMockBodyTemperature("3", 37.5)
            )

            whenever(healthConnectManager.readBodyTemperatures(start, end))
                .thenReturn(flowOf(*mockTemperatures.toTypedArray()))

            viewModel.loadTemperatureHistory(start, end)
            advanceUntilIdle()

            val initialSize = viewModel.temperatureHistory.value!!.size
            assertEquals(3, initialSize)

            // When
            viewModel.deleteTemperatureReading(recordId)
            advanceUntilIdle()

            // Then
            val history = viewModel.temperatureHistory.value
            assertEquals(2, history!!.size)
            assertFalse(history.any { it.recordId == recordId })
        }
    }

    @Nested
    @DisplayName("Temperature Conversion Tests")
    inner class ConversionTests {

        @Test
        @DisplayName("Should convert Celsius to Fahrenheit correctly")
        fun `should convert Celsius to Fahrenheit correctly`() = runTest {
            // Given
            val start = Instant.now().minus(1, ChronoUnit.DAYS)
            val end = Instant.now()
            val celsiusTemp = 37.0
            val expectedFahrenheit = 98.6

            val mockTemperatures = listOf(
                createMockBodyTemperature("1", celsiusTemp)
            )

            whenever(healthConnectManager.readBodyTemperatures(start, end))
                .thenReturn(flowOf(*mockTemperatures.toTypedArray()))

            // When
            viewModel.loadTemperatureHistory(start, end)
            advanceUntilIdle()

            // Then
            val history = viewModel.temperatureHistory.value
            assertNotNull(history)
            assertEquals(1, history!!.size)
            assertEquals(celsiusTemp, history[0].temperatureCelsius, 0.1)
            assertEquals(expectedFahrenheit, history[0].temperatureFahrenheit, 0.1)
        }

        @Test
        @DisplayName("Should format temperature values correctly")
        fun `should format temperature values correctly`() = runTest {
            // Given
            val start = Instant.now().minus(1, ChronoUnit.DAYS)
            val end = Instant.now()
            val mockTemperatures = listOf(
                createMockBodyTemperature("1", 36.789)
            )

            whenever(healthConnectManager.readBodyTemperatures(start, end))
                .thenReturn(flowOf(*mockTemperatures.toTypedArray()))

            // When
            viewModel.loadTemperatureHistory(start, end)
            advanceUntilIdle()

            // Then
            val history = viewModel.temperatureHistory.value
            assertNotNull(history)
            val formatted = history!![0].getFormattedCelsius()
            assertTrue(formatted.contains("°C"))
            assertTrue(formatted.matches(Regex("\\d+\\.\\d°C")))
        }
    }

    @Nested
    @DisplayName("Loading State Tests")
    inner class LoadingStateTests {

        @Test
        @DisplayName("Should set loading state during operation")
        fun `should set loading state during operation`() = runTest {
            // Given
            val start = Instant.now().minus(30, ChronoUnit.DAYS)
            val end = Instant.now()

            whenever(healthConnectManager.readBodyTemperatures(start, end))
                .thenReturn(flowOf())

            // When
            viewModel.loadTemperatureHistory(start, end)

            // Then - loading should be true initially
            // After advanceUntilIdle, it should be false
            advanceUntilIdle()
            assertFalse(viewModel.isLoading.value!!)
        }

        @Test
        @DisplayName("Should clear loading state after error")
        fun `should clear loading state after error`() = runTest {
            // Given
            val start = Instant.now().minus(30, ChronoUnit.DAYS)
            val end = Instant.now()

            whenever(healthConnectManager.readBodyTemperatures(start, end))
                .thenThrow(RuntimeException("Error"))

            // When
            viewModel.loadTemperatureHistory(start, end)
            advanceUntilIdle()

            // Then
            assertFalse(viewModel.isLoading.value!!)
        }
    }

    // Helper Methods
    private fun createMockBodyTemperature(
        id: String,
        tempCelsius: Double
    ): BodyTemperature {
        return BodyTemperature(
            recordId = id,
            temperature = androidx.health.connect.client.units.Temperature.celsius(tempCelsius),
            time = Instant.now(),
            zoneOffset = java.time.ZoneOffset.UTC
        )
    }

    private fun createMockBodyTemperatureWithTime(
        id: String,
        tempCelsius: Double,
        time: Instant
    ): BodyTemperature {
        return BodyTemperature(
            recordId = id,
            temperature = androidx.health.connect.client.units.Temperature.celsius(tempCelsius),
            time = time,
            zoneOffset = java.time.ZoneOffset.UTC
        )
    }
}
