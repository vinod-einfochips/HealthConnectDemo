# Health Connect Body Temperature Tracker

An Android application that integrates with Google Health Connect to record and manage body temperature data.

## Features

- ✅ Record body temperature in Celsius
- ✅ Integration with Google Health Connect
- ✅ MVVM Architecture
- ✅ XML-based UI with ViewBinding
- ✅ Edge-to-Edge display support (Android 15+)
- ✅ Dependency Injection with Hilt
- ✅ Material Design 3 components
- ✅ Permission handling for Health Connect

## Technical Stack

- **Language**: Kotlin
- **Min SDK**: 26 (Android 8.0)
- **Target SDK**: 35 (Android 15)
- **Compile SDK**: 35
- **Architecture**: MVVM (Model-View-ViewModel)
- **UI**: XML layouts with ViewBinding
- **Dependency Injection**: Hilt
- **Health API**: Health Connect Client

## Project Structure

```
app/
├── src/main/
│   ├── java/com/example/healthconnectdemo/
│   │   ├── MainActivity.kt                    # Main activity with edge-to-edge support
│   │   ├── HealthConnectApp.kt               # Application class for Hilt
│   │   ├── di/
│   │   │   └── AppModule.kt                  # Hilt dependency injection module
│   │   ├── healthconnect/
│   │   │   └── HealthConnectManager.kt       # Health Connect API wrapper
│   │   └── viewmodel/
│   │       └── MainViewModel.kt              # ViewModel for business logic
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml             # Main screen layout
│   │   ├── values/
│   │   │   ├── colors.xml
│   │   │   ├── strings.xml
│   │   │   └── themes.xml                    # Light theme with edge-to-edge
│   │   └── values-night/
│   │       └── themes.xml                    # Dark theme with edge-to-edge
│   └── AndroidManifest.xml
└── build.gradle
```

## Prerequisites

1. **Android Studio**: Latest version (Hedgehog or newer recommended)
2. **Android SDK 35**: Install via Android Studio SDK Manager
3. **Health Connect App**: Must be installed on the device/emulator
   - Download from [Google Play Store](https://play.google.com/store/apps/details?id=com.google.android.apps.healthdata)

## Setup Instructions

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd windsurf-project
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the project directory

3. **Sync Gradle**
   - Android Studio will automatically prompt to sync
   - Or manually: File → Sync Project with Gradle Files

4. **Install Health Connect**
   - On your device/emulator, install Health Connect from Play Store
   - Or use adb: `adb install <health-connect.apk>`

5. **Run the app**
   - Connect your device or start an emulator
   - Click Run (Shift + F10)

## Usage

1. **Launch the app**
   - The app will check if Health Connect is available

2. **Grant Permissions**
   - Click "Check/Request Permissions"
   - Grant body temperature read/write permissions

3. **Record Temperature**
   - Enter temperature value in Celsius (20-45°C)
   - Click "Record Temperature"
   - Data will be saved to Health Connect

4. **View Status**
   - Permission status is displayed at the bottom
   - Toast messages show success/error feedback

## Key Components

### MainActivity
- Implements edge-to-edge display
- Handles permission requests
- Observes ViewModel LiveData
- Uses ViewBinding for type-safe view access

### MainViewModel
- Manages UI state and business logic
- Communicates with HealthConnectManager
- Validates temperature input
- Exposes LiveData for UI observation

### HealthConnectManager
- Wraps Health Connect API calls
- Handles permission checks
- Writes body temperature records
- Reads temperature history

## Edge-to-Edge Support

The app implements modern edge-to-edge display:
- Transparent system bars
- Content extends behind status/navigation bars
- Proper window insets handling
- Adaptive for light/dark themes

## Dependencies

```gradle
// Core Android
androidx.core:core-ktx:1.13.1
androidx.appcompat:appcompat:1.7.0
com.google.android.material:material:1.12.0

// Lifecycle & ViewModel
androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4
androidx.lifecycle:lifecycle-livedata-ktx:2.8.4

// Health Connect
androidx.health.connect:connect-client:1.1.0-alpha06

// Hilt (Dependency Injection)
com.google.dagger:hilt-android:2.46.1

// Edge-to-Edge
androidx.core:core-splashscreen:1.0.1
```

## Permissions

The app requires the following Health Connect permissions:
- `android.permission.health.READ_BODY_TEMPERATURE`
- `android.permission.health.WRITE_BODY_TEMPERATURE`

## Testing

- Device/Emulator must have Health Connect installed
- Minimum Android 8.0 (API 26)
- Recommended: Android 15 (API 35) for full edge-to-edge experience

## Troubleshooting

**Health Connect not available**
- Install Health Connect from Play Store
- Ensure device API level is 26+

**Permissions not working**
- Open Health Connect app
- Check app permissions manually
- Reinstall the app if needed

**Build errors**
- Sync Gradle files
- Clean and rebuild project
- Check SDK 35 is installed

## License

This project is for educational purposes.

## Author

Created with Kotlin and ❤️ for Android Health Connect integration.
