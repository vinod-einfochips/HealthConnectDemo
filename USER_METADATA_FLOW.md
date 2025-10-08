# User Metadata Flow Diagram

## Writing Temperature with User Metadata

```
MainActivity
    |
    | User enters: Temperature: 37.5°C, Name: "John Doe", Type: PATIENT
    |
    v
val userInfo = UserInfo("John Doe", UserType.PATIENT)
viewModel.recordTemperature(37.5, userInfo)
    |
    v
MainViewModel.recordTemperature()
    |
    v
HealthConnectManager.writeBodyTemperature()
    |
    | Encodes: "John Doe|PATIENT|null" into clientRecordId
    |
    v
Health Connect Storage
    - Temperature: 37.5°C
    - Time: 2024-01-15 14:30:00
    - Metadata.clientRecordId: "John Doe|PATIENT|null"
```

## Reading Temperature with User Metadata

```
TemperatureHistoryActivity
    |
    v
HistoryViewModel.loadTemperatureHistory()
    |
    v
HealthConnectManager.readBodyTemperatures()
    |
    | Reads from Health Connect
    | Decodes clientRecordId: "John Doe|PATIENT|null"
    |
    v
BodyTemperature(
    temperature = 37.5°C,
    time = ...,
    userInfo = UserInfo("John Doe", PATIENT, null)
)
    |
    v
Display in UI:
    Temperature: 37.5°C
    Recorded by: John Doe (Patient)
```

## Data Structure

### UserInfo
- userName: String (e.g., "John Doe")
- userType: UserType (PATIENT, DOCTOR, NURSE, CAREGIVER, SELF, OTHER)
- userId: String? (optional)

### Storage Format
Stored in Health Connect's clientRecordId field as:
```
userName|userType|userId
Example: "John Doe|PATIENT|12345"
```

## Quick Integration

### Minimal (2 lines):
```kotlin
val userInfo = UserInfo("Self", UserType.SELF)
viewModel.recordTemperature(temperature, userInfo)
```

### With UI Input:
```kotlin
val userName = editText.text.toString()
val userType = spinner.selectedItem as UserType
val userInfo = UserInfo(userName, userType)
viewModel.recordTemperature(temperature, userInfo)
```
