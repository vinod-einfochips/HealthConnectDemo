# Runtime Permission Implementation

## Overview
Implemented proper runtime permission handling for Health Connect in MainActivity with all strings externalized to strings.xml.

## Changes Made

### 1. MainActivity.kt

#### Added Dependencies
```kotlin
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import javax.inject.Inject
```

#### Injected HealthConnectManager
```kotlin
@Inject
lateinit var healthConnectManager: HealthConnectManager
```

#### Runtime Permission Flow
```kotlin
private fun requestPermissions() {
    if (healthConnectManager.checkHealthConnectIsAvailable()) {
        showInstallHealthConnectPopup()
    } else {
        lifecycleScope.launch {
            try {
                val granted = healthConnectManager.hasAllPermissions()
                if (granted) {
                    // Permissions already granted
                    Toast.makeText(this@MainActivity,
                        getString(R.string.permissions_already_granted),
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.checkPermissions()
                } else {
                    // Request permissions
                    permissionLauncher.launch(healthConnectManager.permissions)
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
```

#### Permission Launcher
```kotlin
private val permissionLauncher =
    registerForActivityResult(
        PermissionController.createRequestPermissionResultContract()
    ) { result ->
        if (result.containsAll(healthConnectManager.permissions)) {
            // Permissions granted
            Toast.makeText(this,
                getString(R.string.permissions_granted_success),
                Toast.LENGTH_SHORT
            ).show()
            viewModel.checkPermissions()
        } else {
            // Permissions denied
            showEnableHealthPermissionPopup()
        }
    }
```

#### New Dialog Methods

1. **showInstallHealthConnectPopup()** - When Health Connect is not installed
2. **showEnableHealthPermissionPopup()** - When permissions are denied
3. **openPlayStoreForHealthConnect()** - Opens Play Store to install Health Connect

### 2. strings.xml

Added 40+ string resources for:
- Permission messages
- Health Connect messages
- Button labels
- Temperature input messages
- Status messages
- Titles
- User types

## Permission Flow

```
User clicks "Grant Permissions"
    |
    v
Check if Health Connect is installed
    |
    ├─ Not Installed → Show Install Dialog → Open Play Store
    |
    └─ Installed → Check if permissions granted
         |
         ├─ Already Granted → Show Toast "Permissions already granted"
         |
         └─ Not Granted → Launch Permission Request
              |
              ├─ User Grants → Show Success Toast → Update UI
              |
              └─ User Denies → Show Enable Permission Dialog
```

## Key Features

### 1. Smart Permission Checking
- Checks if Health Connect is available
- Checks if permissions are already granted
- Only requests if needed

### 2. User-Friendly Messages
- All messages externalized to strings.xml
- Clear, informative dialogs
- Proper error handling

### 3. Proper Error Handling
```kotlin
try {
    val granted = healthConnectManager.hasAllPermissions()
    // Handle permission check
} catch (e: Exception) {
    Log.e(TAG, "Error checking permissions", e)
    Toast.makeText(this,
        getString(R.string.error_checking_permissions),
        Toast.LENGTH_SHORT
    ).show()
}
```

### 4. Logging
- Added TAG constant for logging
- Logs permission status
- Logs errors

## String Resources Added

### Permission Messages
- `permissions_already_granted`
- `permissions_granted_success`
- `error_checking_permissions`
- `permissions_required`
- `permissions_required_message`
- `permissions_needed_to_continue`
- `grant_permissions`

### Health Connect Messages
- `health_connect_required`
- `health_connect_install_message`
- `health_connect_not_available`
- `health_connect_not_available_message`
- `health_connect_not_found`
- `health_connect_not_installed_message`
- `health_connect_permissions_title`
- `health_connect_permissions_message`
- `grant_temperature_permissions`
- `navigate_to_permissions`
- `could_not_open_health_connect`
- `health_permissions_required`

### Button Labels
- `install`
- `cancel`
- `ok`
- `open_health_connect`
- `open_play_store`
- `record_temperature`
- `view_history`
- `check_permissions`

### Temperature Input
- `temperature_hint`
- `enter_temperature`
- `enter_valid_number`
- `temperature_recorded`
- `temperature_recorded_with_user`
- `error_recording_temperature`

### Status Messages
- `health_connect_granted`
- `health_connect_not_granted`
- `health_connect_unknown`
- `permissions_granted_status`

### Titles
- `body_temperature_tracker`
- `temperature_history`

### User Types
- `user_type_patient`
- `user_type_doctor`
- `user_type_nurse`
- `user_type_caregiver`
- `user_type_self`
- `user_type_other`

## Usage Examples

### Request Permissions
```kotlin
binding.btnCheckPermissions.setOnClickListener {
    requestHealthConnectPermissions()
}
```

### Check Permission Status
```kotlin
lifecycleScope.launch {
    val granted = healthConnectManager.hasAllPermissions()
    if (granted) {
        // Permissions granted
    } else {
        // Permissions not granted
    }
}
```

### Handle Permission Result
```kotlin
private val permissionLauncher =
    registerForActivityResult(
        PermissionController.createRequestPermissionResultContract()
    ) { result ->
        if (result.containsAll(healthConnectManager.permissions)) {
            // Success
        } else {
            // Denied
        }
    }
```

## Testing

### Test Scenarios

1. **First Launch (No Permissions)**
   - Click "Grant Permissions"
   - Should request permissions
   - Grant permissions
   - Should show success toast
   - UI should update

2. **Already Granted**
   - Click "Grant Permissions"
   - Should show "Permissions already granted" toast
   - No permission dialog

3. **Health Connect Not Installed**
   - Click "Grant Permissions"
   - Should show install dialog
   - Click "Install"
   - Should open Play Store

4. **Permissions Denied**
   - Click "Grant Permissions"
   - Deny permissions
   - Should show enable permission dialog
   - Can retry or cancel

## Benefits

1. ✅ **Proper Runtime Permission Handling**
2. ✅ **All Strings Externalized** (easy localization)
3. ✅ **Smart Permission Checking** (avoids unnecessary requests)
4. ✅ **User-Friendly Dialogs**
5. ✅ **Proper Error Handling**
6. ✅ **Logging for Debugging**
7. ✅ **Clean Code Structure**

## Notes

- Uses Hilt dependency injection for `HealthConnectManager`
- All permission checks are done asynchronously with coroutines
- Proper error handling with try-catch blocks
- All user-facing strings are in strings.xml
- Follows Android best practices for runtime permissions
