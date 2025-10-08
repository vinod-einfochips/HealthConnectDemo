# Health Connect Permission Setup Guide

## How It Works Now

When you open the app, it will:

1. **Request Body Sensor Permissions** (Android system dialog)
   - Grant or deny as needed

2. **Show Health Connect Permission Dialog**
   - Explains what permissions are needed
   - Provides "Open Health Connect" button

3. **Opens Health Connect App**
   - Takes you directly to Health Connect settings
   - You can grant permissions there

4. **Auto-Refresh on Return**
   - When you return to the app, it automatically checks if permissions were granted
   - Status updates immediately

## User Flow

```
App Opens
    ↓
Body Sensor Permission Dialog
    ↓
[Grant/Deny]
    ↓
Health Connect Permission Dialog
    ↓
Click "Open Health Connect"
    ↓
Health Connect App Opens
    ↓
Navigate to: Data and access → Apps → Health Connect Demo
    ↓
Grant: Read Body Temperature
    ↓
Grant: Write Body Temperature
    ↓
Press Back to return to app
    ↓
Status automatically updates to "Granted ✓"
```

## Granting Permissions in Health Connect

### Method 1: Via App Settings (Recommended)
1. App opens Health Connect settings
2. Look for "Permissions" section
3. Find "Body temperature"
4. Toggle ON for both Read and Write
5. Return to the app

### Method 2: Via Health Connect Main Screen
1. Open Health Connect app manually
2. Go to "Data and access"
3. Tap "Apps"
4. Find "Health Connect Demo"
5. Grant "Body temperature" permissions
6. Return to the app

### Method 3: Via Android Settings
1. Settings → Apps → Health Connect
2. Permissions → Body sensors
3. Allow
4. Open Health Connect app
5. Follow Method 2

## Permission Status Indicators

The app shows two status indicators:

1. **Body Sensor: [Status]**
   - Shows Android system permission status
   - Required for direct sensor access

2. **Health Connect: [Status]**
   - Shows Health Connect permission status
   - Required to read/write temperature data

Both should show "Granted ✓" for full functionality.

## Troubleshooting

### Issue: Health Connect App Doesn't Open

**Solution 1: Check if Health Connect is installed**
```bash
adb shell pm list packages | grep healthdata
```

**Solution 2: Install Health Connect**
- The app will show a dialog to open Play Store
- Or manually search "Health Connect" in Play Store

**Solution 3: Update Health Connect**
- Open Play Store
- Search "Health Connect"
- Update if available

### Issue: Can't Find Permission Settings

**Path in Health Connect:**
```
Health Connect App
  → Data and access
    → Apps
      → Health Connect Demo
        → Body temperature (Toggle ON)
```

### Issue: Permissions Not Updating

**Solution:**
1. Close and reopen the app
2. Or click "Check/Request Permissions" button
3. The app checks permissions in `onResume()`

### Issue: "Health Connect Not Available"

**Reasons:**
- Health Connect not installed
- Device doesn't support Health Connect (Android 8.0+ required)
- Health Connect disabled in settings

**Solution:**
- Install from Play Store
- Enable in Android Settings → Apps

## Testing Permissions

### Test 1: Fresh Install
```bash
# Uninstall app
adb uninstall com.example.healthconnectdemo

# Install fresh
adb install app/build/outputs/apk/debug/app-debug.apk

# Open app
adb shell am start -n com.example.healthconnectdemo/.MainActivity
```

### Test 2: Check Current Permissions
```bash
# Check body sensor permission
adb shell dumpsys package com.example.healthconnectdemo | grep BODY_SENSORS

# Open Health Connect
adb shell am start -n com.google.android.apps.healthdata/.MainActivity
```

### Test 3: Revoke and Re-grant
1. Revoke in Health Connect
2. Return to app
3. Click "Check/Request Permissions"
4. Grant again
5. Verify status updates

## Developer Notes

### Opening Health Connect App

The app uses multiple fallback methods:

1. **Primary**: Open Health Connect app settings
   ```kotlin
   Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
   data = Uri.fromParts("package", "com.google.android.apps.healthdata", null)
   ```

2. **Fallback**: Open Health Connect main screen
   ```kotlin
   packageManager.getLaunchIntentForPackage("com.google.android.apps.healthdata")
   ```

3. **Last Resort**: Show Play Store dialog

### Auto-Refresh on Resume

```kotlin
override fun onResume() {
    super.onResume()
    updateBodySensorPermissionStatus()
    viewModel.checkPermissions()
}
```

This ensures permissions are checked whenever user returns from Health Connect.

### Permission Check Flow

```kotlin
HealthConnectManager.hasAllPermissions()
    ↓
healthConnectClient.permissionController.getGrantedPermissions()
    ↓
Compare with required permissions
    ↓
Update UI status
```

## Best Practices

1. ✅ **Always check permissions before recording data**
2. ✅ **Show clear instructions to users**
3. ✅ **Auto-refresh status when app resumes**
4. ✅ **Provide manual refresh button**
5. ✅ **Handle Health Connect not installed case**
6. ✅ **Show meaningful error messages**

## User Instructions

### For End Users

**To use this app:**

1. Install the app
2. When prompted, grant Body Sensor permission
3. Tap "Open Health Connect" when asked
4. In Health Connect, find this app under "Apps"
5. Enable Body Temperature permissions
6. Return to the app
7. You should see "Granted ✓" for both permissions
8. Now you can record temperature data!

**If you see "Not Granted":**
- Click "Check/Request Permissions" button
- Follow the prompts to open Health Connect
- Make sure to enable Body Temperature permissions
- Return to the app

## Security & Privacy

- All health data is stored in Health Connect
- Your data is encrypted and secure
- Only this app can access data you grant permission for
- You can revoke permissions anytime in Health Connect
- No data is sent to external servers

## References

- [Health Connect Documentation](https://developer.android.com/health-and-fitness/guides/health-connect)
- [Health Connect Permissions](https://developer.android.com/health-and-fitness/guides/health-connect/permissions)
- [Health Connect on Play Store](https://play.google.com/store/apps/details?id=com.google.android.apps.healthdata)

---

**Last Updated**: 2025-09-30
**App Version**: 1.0
**Health Connect Version**: Latest
**Min Android**: 8.0 (API 26)
