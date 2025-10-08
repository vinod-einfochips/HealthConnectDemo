# Build Variants and Product Flavors Guide

## Overview
The project is configured with 3 product flavors (Dev, QA, Prod) and 2 build types (Debug, Release), creating 6 build variants total.

## Product Flavors

### 1. Dev (Development)
**Purpose:** Development and testing

**Configuration:**
- **Application ID:** `com.example.healthconnectdemo.dev`
- **App Name:** Health Connect (Dev)
- **Base URL:** `https://dev-api.healthconnect.com`
- **Version Suffix:** `-dev`
- **Logging:** Enabled
- **Crash Reporting:** Disabled
- **Debug Menu:** Enabled
- **Mock Data:** Enabled
- **Network Timeout:** 60 seconds
- **Environment Color:** Orange (#FF9800)

**Use Cases:**
- Local development
- Feature testing
- Debugging
- Mock data testing

### 2. QA (Quality Assurance)
**Purpose:** Testing and quality assurance

**Configuration:**
- **Application ID:** `com.example.healthconnectdemo.qa`
- **App Name:** Health Connect (QA)
- **Base URL:** `https://qa-api.healthconnect.com`
- **Version Suffix:** `-qa`
- **Logging:** Enabled
- **Crash Reporting:** Enabled
- **Debug Menu:** Enabled
- **Mock Data:** Disabled
- **Network Timeout:** 45 seconds
- **Environment Color:** Blue (#2196F3)

**Use Cases:**
- QA testing
- Integration testing
- UAT (User Acceptance Testing)
- Bug verification

### 3. Prod (Production)
**Purpose:** Production release

**Configuration:**
- **Application ID:** `com.example.healthconnectdemo`
- **App Name:** Health Connect
- **Base URL:** `https://api.healthconnect.com`
- **Version Suffix:** None
- **Logging:** Disabled
- **Crash Reporting:** Enabled
- **Debug Menu:** Disabled
- **Mock Data:** Disabled
- **Network Timeout:** 30 seconds
- **Environment Color:** Green (#4CAF50)

**Use Cases:**
- Production release
- App Store distribution
- End users

## Build Types

### Debug
- **Debuggable:** Yes
- **Minification:** Disabled
- **Shrink Resources:** Disabled
- **Debug Mode:** Enabled
- **ProGuard:** Disabled

### Release
- **Debuggable:** No
- **Minification:** Enabled
- **Shrink Resources:** Enabled
- **Debug Mode:** Disabled
- **ProGuard:** Enabled

## Build Variants

The combination of flavors and build types creates 6 variants:

| Variant | Flavor | Build Type | Use Case |
|---------|--------|------------|----------|
| **devDebug** | Dev | Debug | Daily development |
| **devRelease** | Dev | Release | Dev environment testing |
| **qaDebug** | QA | Debug | QA debugging |
| **qaRelease** | QA | Release | QA testing |
| **prodDebug** | Prod | Debug | Production debugging |
| **prodRelease** | Prod | Release | Production release |

## Building Variants

### Build Specific Variant

#### Using Gradle
```bash
# Dev Debug
./gradlew assembleDevDebug

# QA Release
./gradlew assembleQaRelease

# Prod Release
./gradlew assembleProdRelease
```

#### Using Android Studio
1. Open "Build Variants" panel (View → Tool Windows → Build Variants)
2. Select desired variant from dropdown
3. Build → Build Bundle(s) / APK(s) → Build APK(s)

### Build All Variants
```bash
./build_all_variants.sh
```

This will build all 6 variants and place APKs in:
```
app/build/outputs/apk/
├── dev/
│   ├── debug/
│   └── release/
├── qa/
│   ├── debug/
│   └── release/
└── prod/
    ├── debug/
    └── release/
```

## APK Naming Convention

APKs are automatically named with the following format:
```
HealthConnect-{flavor}-{buildType}-v{versionName}-{versionCode}-{timestamp}.apk
```

**Example:**
```
HealthConnect-dev-debug-v1.0-dev-1-20240107-1430.apk
HealthConnect-qa-release-v1.0-qa-1-20240107-1430.apk
HealthConnect-prod-release-v1.0-1-20240107-1430.apk
```

## Accessing Configuration in Code

### Using AppConfig Object
```kotlin
import com.example.healthconnectdemo.config.AppConfig

// Get base URL
val baseUrl = AppConfig.baseUrl

// Check environment
if (AppConfig.isDevelopment) {
    // Development-specific code
}

if (AppConfig.isProduction) {
    // Production-specific code
}

// Enable logging conditionally
if (AppConfig.isLoggingEnabled) {
    Log.d("TAG", "Debug message")
}

// Get version info
val versionInfo = AppConfig.getVersionInfo()
// Returns: "v1.0 (1) - Development"

// Print configuration
AppConfig.printConfig()
```

### Using BuildConfig Directly
```kotlin
import com.example.healthconnectdemo.BuildConfig

val baseUrl = BuildConfig.BASE_URL
val environment = BuildConfig.ENVIRONMENT
val isLogging = BuildConfig.ENABLE_LOGGING
```

### Using Resources
```kotlin
// Get from resources
val envName = getString(R.string.environment_name)
val apiUrl = getString(R.string.api_base_url)
val enableDebugMenu = resources.getBoolean(R.bool.enable_debug_menu)
val timeout = resources.getInteger(R.integer.network_timeout)
```

## Environment-Specific Resources

Each flavor can have its own resources:

```
app/src/
├── main/          # Shared resources
├── dev/           # Dev-specific resources
│   └── res/
│       └── values/
│           └── config.xml
├── qa/            # QA-specific resources
│   └── res/
│       └── values/
│           └── config.xml
└── prod/          # Prod-specific resources
    └── res/
        └── values/
            └── config.xml
```

## Feature Flags

Feature flags are configured per environment:

```xml
<!-- Dev -->
<bool name="enable_debug_menu">true</bool>
<bool name="enable_mock_data">true</bool>
<bool name="enable_strict_mode">true</bool>

<!-- QA -->
<bool name="enable_debug_menu">true</bool>
<bool name="enable_mock_data">false</bool>
<bool name="enable_strict_mode">false</bool>

<!-- Prod -->
<bool name="enable_debug_menu">false</bool>
<bool name="enable_mock_data">false</bool>
<bool name="enable_strict_mode">false</bool>
```

## Installing Multiple Variants

Because each flavor has a different application ID, you can install multiple variants on the same device:

```bash
# Install all three environments simultaneously
adb install app/build/outputs/apk/dev/debug/HealthConnect-dev-debug-*.apk
adb install app/build/outputs/apk/qa/debug/HealthConnect-qa-debug-*.apk
adb install app/build/outputs/apk/prod/debug/HealthConnect-prod-debug-*.apk
```

All three apps will appear separately on the device with different names and icons.

## CI/CD Integration

### GitHub Actions Example
```yaml
name: Build All Variants

on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest
    strategy:
      matrix:
        variant: [DevDebug, QaRelease, ProdRelease]
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 17
        uses: actions/setup-java@v2
        with:
          java-version: '17'
      - name: Build ${{ matrix.variant }}
        run: ./gradlew assemble${{ matrix.variant }}
      - name: Upload APK
        uses: actions/upload-artifact@v2
        with:
          name: ${{ matrix.variant }}-apk
          path: app/build/outputs/apk/**/*.apk
```

### Gradle Tasks
```bash
# List all available tasks
./gradlew tasks

# Build specific variant
./gradlew assembleDevDebug
./gradlew assembleQaRelease
./gradlew assembleProdRelease

# Install specific variant
./gradlew installDevDebug
./gradlew installQaRelease

# Run tests for specific variant
./gradlew testDevDebugUnitTest
./gradlew testQaReleaseUnitTest
```

## Signing Configuration

For release builds, add signing configuration:

```gradle
android {
    signingConfigs {
        release {
            storeFile file("keystore/release.keystore")
            storePassword System.getenv("KEYSTORE_PASSWORD")
            keyAlias System.getenv("KEY_ALIAS")
            keyPassword System.getenv("KEY_PASSWORD")
        }
    }
    
    buildTypes {
        release {
            signingConfig signingConfigs.release
            // ... other config
        }
    }
}
```

## Best Practices

### 1. Development Workflow
- Use **devDebug** for daily development
- Use **devRelease** to test release optimizations
- Enable all debugging features in Dev

### 2. QA Testing
- Use **qaDebug** for debugging QA issues
- Use **qaRelease** for final QA testing
- Keep logging enabled for issue tracking

### 3. Production Release
- Only use **prodRelease** for production
- Disable all debugging features
- Enable crash reporting
- Test thoroughly in QA first

### 4. Version Management
- Increment versionCode for each release
- Use semantic versioning for versionName
- Add build metadata to version suffix

### 5. Environment URLs
- Use environment variables for sensitive URLs
- Never hardcode production credentials
- Use different API keys per environment

## Troubleshooting

### Build Variant Not Showing
1. Sync Gradle files
2. Invalidate caches (File → Invalidate Caches / Restart)
3. Check Build Variants panel

### Wrong Configuration Loading
1. Clean project: `./gradlew clean`
2. Rebuild: `./gradlew build`
3. Check selected build variant in Android Studio

### APK Not Installing
1. Uninstall previous version
2. Check application ID matches
3. Enable "Install from Unknown Sources"

## Quick Reference

### Common Commands
```bash
# Build dev debug
./gradlew assembleDevDebug

# Build qa release
./gradlew assembleQaRelease

# Build prod release
./gradlew assembleProdRelease

# Build all variants
./build_all_variants.sh

# Install on device
./gradlew installDevDebug

# List variants
./gradlew tasks --all | grep assemble
```

### File Locations
- **Build config:** `app/build.gradle`
- **App config:** `app/src/main/java/com/example/healthconnectdemo/config/AppConfig.kt`
- **Dev resources:** `app/src/dev/res/values/config.xml`
- **QA resources:** `app/src/qa/res/values/config.xml`
- **Prod resources:** `app/src/prod/res/values/config.xml`
- **APK output:** `app/build/outputs/apk/`

## Summary

✅ **3 Product Flavors** - Dev, QA, Prod
✅ **2 Build Types** - Debug, Release
✅ **6 Build Variants** - All combinations
✅ **Environment-Specific Config** - URLs, flags, resources
✅ **BuildConfig Fields** - Compile-time constants
✅ **Custom APK Naming** - Descriptive filenames
✅ **Multiple Installations** - Different app IDs
✅ **Build Script** - Automated building
✅ **AppConfig Utility** - Easy access to config

Your app now supports multiple environments with proper configuration management!
