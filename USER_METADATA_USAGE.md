# User Metadata in Temperature Recording

## Overview
You can now add user metadata (name and type) when recording body temperature in Health Connect.

## Features Added

### 1. UserInfo Model
**Location:** `app/src/main/java/com/example/healthconnectdemo/model/UserInfo.kt`

```kotlin
data class UserInfo(
    val userName: String,
    val userType: UserType,
    val userId: String? = null
)

enum class UserType {
    PATIENT,
    DOCTOR,
    NURSE,
    CAREGIVER,
    SELF,
    OTHER
}
```

### 2. Enhanced HealthConnectManager
The `writeBodyTemperature` method now accepts optional user metadata:

```kotlin
suspend fun writeBodyTemperature(
    temperature: Double,
    time: Instant = Instant.now(),
    userInfo: UserInfo? = null
)
```

### 3. Enhanced MainViewModel
The `recordTemperature` method now accepts optional user metadata:

```kotlin
fun recordTemperature(temperature: Double, userInfo: UserInfo? = null)
```

## How to Use

### Basic Usage (Without User Metadata)
```kotlin
// Record temperature without user info (existing functionality)
viewModel.recordTemperature(37.5)
```

### With User Metadata
```kotlin
// Create user info
val userInfo = UserInfo(
    userName = "John Doe",
    userType = UserType.PATIENT,
    userId = "12345"  // Optional
)

// Record temperature with user info
viewModel.recordTemperature(37.5, userInfo)
```

### Example in MainActivity
```kotlin
binding.btnRecordTemperature.setOnClickListener {
    val temperatureText = binding.etTemperature.text.toString()
    
    if (temperatureText.isEmpty()) {
        Toast.makeText(this, "Please enter a temperature", Toast.LENGTH_SHORT).show()
        return@setOnClickListener
    }

    try {
        val temperature = temperatureText.toDouble()
        
        // Create user info (you can get this from UI inputs)
        val userInfo = UserInfo(
            userName = "John Doe",
            userType = UserType.SELF,
            userId = "user123"
        )
        
        // Record with user metadata
        viewModel.recordTemperature(temperature, userInfo)
    } catch (e: NumberFormatException) {
        Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
    }
}
```

## Reading User Metadata

When reading temperature records, the user information is automatically extracted:

```kotlin
viewModel.loadTemperatureHistory(startTime, endTime)

viewModel.temperatureHistory.observe(this) { temperatures ->
    temperatures.forEach { temp ->
        val userInfo = temp.userInfo
        if (userInfo != null) {
            println("Temperature: ${temp.temperature.inCelsius}")
            println("Recorded by: ${userInfo.userName}")
            println("User type: ${userInfo.userType.getDisplayName()}")
            println("User ID: ${userInfo.userId}")
        }
    }
}
```

## UI Enhancement Example

### Add User Input Fields to activity_main.xml

```xml
<!-- User Name Input -->
<com.google.android.material.textfield.TextInputLayout
    android:id="@+id/tilUserName"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="16dp"
    android:hint="User Name (Optional)">
    
    <com.google.android.material.textfield.TextInputEditText
        android:id="@+id/etUserName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:inputType="textPersonName" />
</com.google.android.material.textfield.TextInputLayout>

<!-- User Type Spinner -->
<Spinner
    android:id="@+id/spinnerUserType"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="8dp"
    android:entries="@array/user_types" />
```

### Add to strings.xml

```xml
<string-array name="user_types">
    <item>Self</item>
    <item>Patient</item>
    <item>Doctor</item>
    <item>Nurse</item>
    <item>Caregiver</item>
    <item>Other</item>
</string-array>
```

### Updated MainActivity Code

```kotlin
binding.btnRecordTemperature.setOnClickListener {
    val temperatureText = binding.etTemperature.text.toString()
    val userName = binding.etUserName.text.toString()
    
    if (temperatureText.isEmpty()) {
        Toast.makeText(this, "Please enter a temperature", Toast.LENGTH_SHORT).show()
        return@setOnClickListener
    }

    try {
        val temperature = temperatureText.toDouble()
        
        // Create user info if name is provided
        val userInfo = if (userName.isNotEmpty()) {
            val userTypePosition = binding.spinnerUserType.selectedItemPosition
            val userType = when (userTypePosition) {
                0 -> UserType.SELF
                1 -> UserType.PATIENT
                2 -> UserType.DOCTOR
                3 -> UserType.NURSE
                4 -> UserType.CAREGIVER
                else -> UserType.OTHER
            }
            
            UserInfo(
                userName = userName,
                userType = userType,
                userId = null  // Can be set if you have user ID
            )
        } else {
            null
        }
        
        viewModel.recordTemperature(temperature, userInfo)
    } catch (e: NumberFormatException) {
        Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
    }
}
```

## Data Storage Format

User metadata is stored in the `clientRecordId` field of Health Connect metadata using this format:
```
userName|userType|userId
```

Example:
```
John Doe|PATIENT|12345
```

This approach:
- ✅ Uses Health Connect's built-in metadata field
- ✅ Doesn't require additional database
- ✅ Automatically syncs with Health Connect
- ✅ Can be retrieved when reading records
- ✅ Supports optional fields

## Benefits

1. **Track who recorded the temperature** - Useful in healthcare settings
2. **Differentiate between self-monitoring and professional readings**
3. **Maintain audit trail** - Know which healthcare provider took the measurement
4. **Family health tracking** - Record temperatures for different family members
5. **Multi-user support** - One app can track multiple people

## Notes

- User metadata is **optional** - existing code continues to work without changes
- The `userName` field automatically replaces `|` characters with `_` to avoid parsing issues
- If metadata parsing fails, the temperature record is still valid, just without user info
- User metadata is stored locally in Health Connect and syncs across devices

## Example Use Cases

### 1. Family Health Tracking
```kotlin
val childInfo = UserInfo("Emma", UserType.PATIENT, "child-001")
viewModel.recordTemperature(37.2, childInfo)

val parentInfo = UserInfo("Parent", UserType.CAREGIVER, "parent-001")
viewModel.recordTemperature(36.8, parentInfo)
```

### 2. Healthcare Provider
```kotlin
val nurseInfo = UserInfo("Nurse Sarah", UserType.NURSE, "nurse-123")
viewModel.recordTemperature(38.5, nurseInfo)
```

### 3. Self-Monitoring
```kotlin
val selfInfo = UserInfo("Me", UserType.SELF)
viewModel.recordTemperature(37.0, selfInfo)
```

## Testing

Unit tests have been created but need to be updated to include user metadata scenarios. You can add tests like:

```kotlin
@Test
fun `recordTemperature with user metadata succeeds`() = runTest {
    val userInfo = UserInfo("Test User", UserType.PATIENT, "123")
    viewModel.recordTemperature(37.5, userInfo)
    
    val result = viewModel.temperatureRecorded.value
    assertTrue(result.isSuccess)
    assertTrue(result.getOrNull()?.contains("Test User") == true)
}
```
