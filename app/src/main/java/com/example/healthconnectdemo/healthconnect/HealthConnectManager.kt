package com.example.healthconnectdemo.healthconnect

import android.content.Context
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.BodyTemperatureRecord
import androidx.health.connect.client.records.metadata.Metadata
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import androidx.health.connect.client.units.Temperature
import com.example.healthconnectdemo.model.UserInfo
import com.example.healthconnectdemo.model.UserType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import javax.inject.Inject

class HealthConnectManager
    @Inject
    constructor(
        private val healthConnectClient: HealthConnectClient,
        private val context: Context,
    ) {
        val permissions =
            setOf(
                HealthPermission.getWritePermission(BodyTemperatureRecord::class),
                HealthPermission.getReadPermission(BodyTemperatureRecord::class),
            )

        fun checkHealthConnectIsAvailable(): Boolean =
            HealthConnectClient.getSdkStatus(context) == HealthConnectClient.SDK_UNAVAILABLE_PROVIDER_UPDATE_REQUIRED

        suspend fun hasAllPermissions(): Boolean {
            return healthConnectClient.permissionController.getGrantedPermissions()
                .containsAll(permissions)
        }

        // Permission request is handled by the Activity using PermissionController

        suspend fun readBodyTemperatures(
            start: Instant,
            end: Instant,
        ): Flow<BodyTemperature> =
            flow {
                val request =
                    ReadRecordsRequest(
                        recordType = BodyTemperatureRecord::class,
                        timeRangeFilter = TimeRangeFilter.between(start, end),
                    )
                val response = healthConnectClient.readRecords(request)

                for (record in response.records) {
                    // Extract user info from clientRecordId if available
                    val userInfo = extractUserInfoFromMetadata(record.metadata.clientRecordId)

                    emit(
                        BodyTemperature(
                            recordId = record.metadata.id,
                            temperature = record.temperature,
                            time = record.time,
                            zoneOffset = record.zoneOffset ?: ZoneId.systemDefault().rules.getOffset(record.time),
                            userInfo = userInfo,
                        ),
                    )
                }
            }

        suspend fun writeBodyTemperature(
            temperature: Double,
            time: Instant = Instant.now(),
            userInfo: UserInfo? = null,
        ) {
            val zoneOffset = ZoneId.systemDefault().rules.getOffset(time)

            // Create clientRecordId with user metadata
            val clientRecordId =
                if (userInfo != null) {
                    createClientRecordId(userInfo)
                } else {
                    null
                }

            val record =
                BodyTemperatureRecord(
                    temperature = Temperature.celsius(temperature),
                    time = time,
                    zoneOffset = zoneOffset,
                    metadata =
                        Metadata(
                            recordingMethod = Metadata.RECORDING_METHOD_ACTIVELY_RECORDED,
                            clientRecordId = clientRecordId,
                        ),
                )

            healthConnectClient.insertRecords(listOf(record))
        }

        /**
         * Creates a client record ID that encodes user information
         * Format: userName|userType|userId
         */
        private fun createClientRecordId(userInfo: UserInfo): String {
            return buildString {
                append(userInfo.userName.replace("|", "_"))
                append("|")
                append(userInfo.userType.name)
                if (userInfo.userId != null) {
                    append("|")
                    append(userInfo.userId)
                }
            }
        }

        /**
         * Extracts user information from client record ID
         */
        private fun extractUserInfoFromMetadata(clientRecordId: String?): UserInfo? {
            if (clientRecordId.isNullOrBlank()) return null

            return try {
                val parts = clientRecordId.split("|")
                if (parts.size >= 2) {
                    UserInfo(
                        userName = parts[0],
                        userType = UserType.valueOf(parts[1]),
                        userId = if (parts.size > 2) parts[2] else null,
                    )
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }

        suspend fun deleteBodyTemperature(recordId: String) {
            try {
                healthConnectClient.deleteRecords(
                    recordType = BodyTemperatureRecord::class,
                    recordIdsList = listOf(recordId),
                    clientRecordIdsList = emptyList(),
                )
            } catch (e: Exception) {
                throw Exception("Failed to delete temperature record: ${e.message}")
            }
        }
    }

data class BodyTemperature(
    val recordId: String,
    val temperature: Temperature,
    val time: Instant,
    val zoneOffset: ZoneOffset,
    val userInfo: UserInfo? = null,
)
