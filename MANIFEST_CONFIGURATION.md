# AndroidManifest.xml Configuration for Health Connect

## Complete Manifest Setup

### 1. Permissions Declaration

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

**Purpose:**
- `READ_BODY_TEMPERATURE`: Read temperature data from Health Connect
- `WRITE_BODY_TEMPERATURE`: Write temperature data to Health Connect
- `BODY_SENSORS`: Access body sensors at runtime
- `BODY_SENSORS_BACKGROUND`: Access sensors in background (Android 13+)

### 2. Queries Declaration

```xml
<queries>
    <package android:name="com.google.android.apps.healthdata" />
</queries>
```

**Purpose:**
- Allows the app to check if Health Connect is installed
- Required for `packageManager.getLaunchIntentForPackage()`
- Enables opening Health Connect app from your app

### 3. Application Metadata

```xml
<application>
    <!-- Health Connect metadata -->
    <meta-data
        android:name="androidx.health.platform.client"
        android:value="true" />
</application>
```

**Purpose:**
- Declares that this app is a Health Connect client
- Enables Health Connect to recognize and list this app
- Required for proper Health Connect integration

### 4. Activity Intent Filters

#### Main Activity Intent Filter

```xml
<activity android:name=".MainActivity">
    <!-- Standard launcher intent -->
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
    
    <!-- Health Connect integration intent filter -->
    <intent-filter>
        <action android:name="androidx.health.ACTION_SHOW_PERMISSIONS_RATIONALE" />
    </intent-filter>
</activity>
```

**Purpose:**
- `MAIN` + `LAUNCHER`: Standard app launcher
- `ACTION_SHOW_PERMISSIONS_RATIONALE`: Allows Health Connect to launch your app to show permission rationale

### 5. Activity Alias for Permission Usage

```xml
<activity-alias
    android:name="ViewPermissionUsageActivity"
    android:exported="true"
    android:targetActivity=".MainActivity"
    android:permission="android.permission.START_VIEW_PERMISSION_USAGE">
    <intent-filter>
        <action android:name="android.intent.action.VIEW_PERMISSION_USAGE" />
        <category android:name="android.intent.category.HEALTH_PERMISSIONS" />
    </intent-filter>
</activity-alias>
```

**Purpose:**
- Creates an alias to MainActivity for permission usage viewing
- `VIEW_PERMISSION_USAGE`: Allows users to view how your app uses health data
- `HEALTH_PERMISSIONS` category: Identifies this as a health permission handler
- `START_VIEW_PERMISSION_USAGE`: System permission to start permission usage view

## Complete AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <!-- Health Connect permissions for body temperature -->
    <uses-permission android:name="android.permission.health.READ_BODY_TEMPERATURE" />
    <uses-permission android:name="android.permission.health.WRITE_BODY_TEMPERATURE" />
    
    <!-- Body sensor permissions for direct sensor access (runtime permissions) -->
    <uses-permission android:name="android.permission.BODY_SENSORS" />
    
    <!-- Background body sensor permission (Android 13+) -->
    <uses-permission 
        android:name="android.permission.BODY_SENSORS_BACKGROUND"
        android:minSdkVersion="33" />
    
    <queries>
        <package android:name="com.google.android.apps.healthdata" />
    </queries>

    <application
        android:name=".HealthConnectApp"
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.HealthConnectDemo"
        tools:targetApi="31">
        
        <!-- Health Connect metadata -->
        <meta-data
            android:name="androidx.health.platform.client"
            android:value="true" />
            
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:label="@string/app_name"
            android:theme="@style/Theme.HealthConnectDemo">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
            
            <!-- Health Connect integration intent filter -->
            <intent-filter>
                <action android:name="androidx.health.ACTION_SHOW_PERMISSIONS_RATIONALE" />
            </intent-filter>
        </activity>
        
        <!-- Activity for handling Health Connect permission requests -->
        <activity-alias
            android:name="ViewPermissionUsageActivity"
            android:exported="true"
            android:targetActivity=".MainActivity"
            android:permission="android.permission.START_VIEW_PERMISSION_USAGE">
            <intent-filter>
                <action android:name="android.intent.action.VIEW_PERMISSION_USAGE" />
                <category android:name="android.intent.category.HEALTH_PERMISSIONS" />
            </intent-filter>
        </activity-alias>
    </application>
</manifest>
```

## How These Work Together

### 1. App Discovery
```
Health Connect App
    ↓
Scans for apps with metadata: androidx.health.platform.client
    ↓
Finds your app
    ↓
Lists it under "Data and access" → "Apps"
```

### 2. Permission Rationale
```
User taps your app in Health Connect
    ↓
Health Connect sends ACTION_SHOW_PERMISSIONS_RATIONALE intent
    ↓
Your MainActivity receives intent
    ↓
Show permission explanation to user
```

### 3. Permission Usage View
```
User taps "See all data" in Health Connect
    ↓
Health Connect sends VIEW_PERMISSION_USAGE intent
    ↓
ViewPermissionUsageActivity (alias to MainActivity) handles it
    ↓
Show data usage details
```

### 4. Permission Granting
```
User grants permissions in Health Connect
    ↓
Health Connect stores permission state
    ↓
Your app checks via HealthConnectClient.permissionController
    ↓
Permissions are granted ✓
```

## Testing the Configuration

### Test 1: Verify App is Listed in Health Connect

```bash
# Open Health Connect
adb shell am start -n com.google.android.apps.healthdata/.MainActivity

# Navigate to: Data and access → Apps
# Your app should be listed as "Health Connect Demo"
```

### Test 2: Verify Intent Filters

```bash
# Test permission rationale intent
adb shell am start -a androidx.health.ACTION_SHOW_PERMISSIONS_RATIONALE \
    -n com.example.healthconnectdemo/.MainActivity

# Test permission usage intent
adb shell am start -a android.intent.action.VIEW_PERMISSION_USAGE \
    -c android.intent.category.HEALTH_PERMISSIONS \
    -n com.example.healthconnectdemo/ViewPermissionUsageActivity
```

### Test 3: Verify Metadata

```bash
# Dump app info
adb shell dumpsys package com.example.healthconnectdemo | grep -A 5 "meta-data"

# Should show: androidx.health.platform.client=true
```

### Test 4: Verify Queries

```bash
# Check if app can query Health Connect
adb shell dumpsys package com.example.healthconnectdemo | grep -A 10 "queries"

# Should show: com.google.android.apps.healthdata
```

## Common Issues

### Issue 1: App Not Listed in Health Connect

**Cause**: Missing metadata
**Solution**: Add `<meta-data android:name="androidx.health.platform.client" android:value="true" />`

### Issue 2: Can't Open Health Connect from App

**Cause**: Missing queries declaration
**Solution**: Add `<queries><package android:name="com.google.android.apps.healthdata" /></queries>`

### Issue 3: Permission Rationale Not Showing

**Cause**: Missing intent filter
**Solution**: Add `ACTION_SHOW_PERMISSIONS_RATIONALE` intent filter to MainActivity

### Issue 4: Permission Usage View Not Working

**Cause**: Missing activity-alias
**Solution**: Add `ViewPermissionUsageActivity` activity-alias with proper intent filter

## Security Considerations

### Exported Activities

```xml
android:exported="true"
```

- Required for Health Connect to launch your activities
- Protected by system permission: `START_VIEW_PERMISSION_USAGE`
- Only Health Connect can launch these intents

### Permission Protection

```xml
android:permission="android.permission.START_VIEW_PERMISSION_USAGE"
```

- System-level permission
- Only granted to Health Connect
- Prevents malicious apps from launching your permission screens

## Best Practices

1. ✅ **Always include metadata**: Ensures Health Connect recognizes your app
2. ✅ **Add queries declaration**: Enables checking if Health Connect is installed
3. ✅ **Implement intent filters**: Allows Health Connect to communicate with your app
4. ✅ **Use activity-alias**: Separates permission UI from main app logic
5. ✅ **Declare all permissions**: Even if not using them yet
6. ✅ **Test on real device**: Emulators may not have Health Connect

## References

- [Health Connect Manifest Configuration](https://developer.android.com/health-and-fitness/guides/health-connect/develop/get-started#declare-permissions)
- [Health Connect Permissions](https://developer.android.com/health-and-fitness/guides/health-connect/permissions)
- [Android Manifest Reference](https://developer.android.com/guide/topics/manifest/manifest-intro)
- [Intent Filters](https://developer.android.com/guide/components/intents-filters)

---

**Last Updated**: 2025-09-30
**Android Version**: 8.0+ (API 26+)
**Health Connect Version**: Latest
**Configuration Status**: ✅ Complete
