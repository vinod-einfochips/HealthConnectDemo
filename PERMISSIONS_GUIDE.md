# Permissions Guide - Health Connect Body Temperature App

## Overview

This app requires multiple permissions to access body temperature data through Health Connect and body sensors. All permissions are properly declared in the `AndroidManifest.xml` and requested at runtime.

## Declared Permissions

### 1. Health Connect Permissions

#### READ_BODY_TEMPERATURE
```xml
<uses-permission android:name="android.permission.health.READ_BODY_TEMPERATURE" />
```
- **Purpose**: Read body temperature records from Health Connect
- **Type**: Health Connect permission (requires user consent through Health Connect UI)
- **Required**: Yes
- **When requested**: When user clicks "Check/Request Permissions"

#### WRITE_BODY_TEMPERATURE
```xml
<uses-permission android:name="android.permission.health.WRITE_BODY_TEMPERATURE" />
```
- **Purpose**: Write body temperature records to Health Connect
- **Type**: Health Connect permission (requires user consent through Health Connect UI)
- **Required**: Yes
- **When requested**: When user clicks "Check/Request Permissions"

### 2. Body Sensor Permissions (Runtime)

#### BODY_SENSORS
```xml
<uses-permission android:name="android.permission.BODY_SENSORS" />
```
- **Purpose**: Access body sensors (heart rate, temperature sensors, etc.)
- **Type**: Dangerous permission (requires runtime request)
- **Required**: Yes
- **API Level**: All versions (API 20+)
- **When requested**: On app startup and when clicking "Check/Request Permissions"

#### BODY_SENSORS_BACKGROUND
```xml
<uses-permission 
    android:name="android.permission.BODY_SENSORS_BACKGROUND"
    android:minSdkVersion="33" />
```
- **Purpose**: Access body sensors in the background
- **Type**: Dangerous permission (requires runtime request)
- **Required**: Optional (only for background monitoring)
- **API Level**: Android 13+ (API 33+)
- **When requested**: On app startup (if Android 13+)

## Permission Flow

### App Startup Flow

1. **Check Health Connect Availability**
   - Verify Health Connect is installed
   - Show error if not available

2. **Request Body Sensor Permissions**
   - Check if `BODY_SENSORS` is granted
   - Check if `BODY_SENSORS_BACKGROUND` is granted (Android 13+)
   - Request missing permissions
   - Show rationale dialog if previously denied

3. **Request Health Connect Permissions**
   - After body sensor permissions are granted
   - Launch Health Connect permission UI
   - Request READ and WRITE body temperature permissions

### Permission Request Button Flow

When user clicks "Check/Request Permissions":
1. Check body sensor permissions
2. Request if not granted
3. Then request Health Connect permissions

## Implementation Details

### Manifest Declaration

Located in: `app/src/main/AndroidManifest.xml`

```xml
<!-- Health Connect permissions for body temperature -->
<uses-permission android:name="android.permission.health.READ_BODY_TEMPERATURE" />
<uses-permission android:name="android.permission.health.WRITE_BODY_TEMPERATURE" />

<!-- Body sensor permissions for direct sensor access (runtime permissions) -->
<uses-permission android:name="android.permission.BODY_SENSORS" />

<!-- Background body sensor permission (Android 13+) -->
<uses-permission 
    android:name="android.permission.BODY_SENSORS_BACKGROUND"
    android:minSdkVersion="33" />
```

### Runtime Permission Handling

Located in: `MainActivity.kt`

```kotlin
// Body sensor permission launcher
private val requestBodySensorPermissions = registerForActivityResult(
    ActivityResultContracts.RequestMultiplePermissions()
) { permissions ->
    // Handle result
}

// Health Connect permission launcher
private val requestHealthPermissions = registerForActivityResult(
    PermissionController.createRequestPermissionResultContract()
) { granted ->
    // Handle result
}
```

### Permission Helper

Located in: `utils/PermissionHelper.kt`

Provides utility methods:
- `getBodySensorPermissionsToRequest()` - Get list of permissions to request
- `hasBodySensorsPermission()` - Check if BODY_SENSORS is granted
- `hasBodySensorsBackgroundPermission()` - Check if BODY_SENSORS_BACKGROUND is granted
- `hasAllBodySensorPermissions()` - Check if all permissions are granted
- `getPermissionRationale()` - Get user-friendly permission explanation

## User Experience

### Permission Status Display

The app shows two status indicators:
1. **Body Sensor**: Shows if body sensor permissions are granted
2. **Health Connect**: Shows if Health Connect permissions are granted

### Permission Rationale

If user denies permission, a dialog explains:
- Why the permission is needed
- What functionality requires it
- Option to grant or cancel

### Permission Denial Handling

- **First denial**: Request again when user clicks permission button
- **Permanent denial**: Show toast message explaining limitation
- **Partial grant**: Show which permissions were denied

## Testing Permissions

### Grant Permissions

1. Install app
2. App automatically requests body sensor permissions
3. Tap "Allow" for BODY_SENSORS
4. Tap "Allow" for BODY_SENSORS_BACKGROUND (Android 13+)
5. Health Connect UI opens
6. Grant READ_BODY_TEMPERATURE
7. Grant WRITE_BODY_TEMPERATURE

### Revoke Permissions

**Body Sensor Permissions:**
- Settings → Apps → Health Connect Demo → Permissions → Body sensors → Deny

**Health Connect Permissions:**
- Open Health Connect app
- Data and access → Apps → Health Connect Demo
- Remove permissions

### Test Permission Flow

1. **Fresh Install**: All permissions should be requested
2. **Partial Grant**: Deny body sensors, verify app shows correct status
3. **Re-request**: Click permission button, verify it requests again
4. **All Granted**: Verify both status indicators show "Granted ✓"

## Troubleshooting

### Permission Not Showing

**Issue**: Body sensor permission not requested

**Solution**: 
- Check AndroidManifest.xml has `<uses-permission android:name="android.permission.BODY_SENSORS" />`
- Verify targetSdk is 26 or higher
- Clean and rebuild project

### Health Connect Permission Fails

**Issue**: Health Connect permission request fails

**Solution**:
- Verify Health Connect app is installed
- Check app has `<queries>` declaration for Health Connect
- Ensure Health Connect permissions are declared in manifest

### Background Permission Not Available

**Issue**: BODY_SENSORS_BACKGROUND not showing

**Solution**:
- This permission only exists on Android 13+ (API 33+)
- Check device Android version
- Verify `android:minSdkVersion="33"` is set in manifest

## Permission Best Practices

1. ✅ **Request at appropriate time**: Body sensors on startup, Health Connect when needed
2. ✅ **Show rationale**: Explain why permission is needed
3. ✅ **Handle denial gracefully**: Show clear feedback
4. ✅ **Check before use**: Always verify permission before accessing sensors
5. ✅ **Minimal permissions**: Only request what's actually needed

## Security Notes

- Body sensor data is sensitive health information
- Always use HTTPS for any network transmission
- Store data securely using Android Keystore if needed
- Follow HIPAA/GDPR guidelines if applicable
- Request background permission only if truly needed

## References

- [Android Permissions Documentation](https://developer.android.com/guide/topics/permissions/overview)
- [Health Connect Documentation](https://developer.android.com/health-and-fitness/guides/health-connect)
- [Body Sensors Permission](https://developer.android.com/reference/android/Manifest.permission#BODY_SENSORS)
- [Runtime Permissions Guide](https://developer.android.com/training/permissions/requesting)

---

**Last Updated**: 2025-09-30
**App Version**: 1.0
**Min SDK**: 26 (Android 8.0)
**Target SDK**: 35 (Android 15)
