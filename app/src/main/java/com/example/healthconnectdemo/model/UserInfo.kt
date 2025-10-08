package com.example.healthconnectdemo.model

/**
 * Represents user information to be stored with temperature records
 */
data class UserInfo(
    val userName: String,
    val userType: UserType,
    val userId: String? = null,
)

/**
 * Types of users who can record temperature
 */
enum class UserType {
    PATIENT,
    DOCTOR,
    NURSE,
    CAREGIVER,
    SELF,
    OTHER,
    ;

    fun getDisplayName(): String {
        return when (this) {
            PATIENT -> "Patient"
            DOCTOR -> "Doctor"
            NURSE -> "Nurse"
            CAREGIVER -> "Caregiver"
            SELF -> "Self"
            OTHER -> "Other"
        }
    }
}
