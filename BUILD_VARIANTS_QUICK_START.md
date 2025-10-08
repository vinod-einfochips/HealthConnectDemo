# Build Variants - Quick Start

## Build Variants Available

| Variant | Environment | Build Type | App ID Suffix |
|---------|-------------|------------|---------------|
| devDebug | Development | Debug | .dev |
| devRelease | Development | Release | .dev |
| qaDebug | QA | Debug | .qa |
| qaRelease | QA | Release | .qa |
| prodDebug | Production | Debug | - |
| prodRelease | Production | Release | - |

## Quick Commands

### Build Specific Variant
```bash
# Development
./gradlew assembleDevDebug
./gradlew assembleDevRelease

# QA
./gradlew assembleQaDebug
./gradlew assembleQaRelease

# Production
./gradlew assembleProdDebug
./gradlew assembleProdRelease
```

### Build All Variants
```bash
./build_all_variants.sh
```

### Install on Device
```bash
./gradlew installDevDebug
./gradlew installQaRelease
./gradlew installProdRelease
```

## Environment Configuration

### Dev
- URL: `https://dev-api.healthconnect.com`
- Logging: ON
- Crash Reporting: OFF
- Debug Menu: ON

### QA
- URL: `https://qa-api.healthconnect.com`
- Logging: ON
- Crash Reporting: ON
- Debug Menu: ON

### Prod
- URL: `https://api.healthconnect.com`
- Logging: OFF
- Crash Reporting: ON
- Debug Menu: OFF

## Using in Code

```kotlin
import com.example.healthconnectdemo.config.AppConfig

// Get base URL
val url = AppConfig.baseUrl

// Check environment
if (AppConfig.isDevelopment) {
    // Dev code
}

// Conditional logging
if (AppConfig.isLoggingEnabled) {
    Log.d("TAG", "Message")
}

// Get version info
val version = AppConfig.getVersionInfo()
```

## APK Locations

After building, APKs are in:
```
app/build/outputs/apk/
├── dev/debug/
├── dev/release/
├── qa/debug/
├── qa/release/
├── prod/debug/
└── prod/release/
```

## Android Studio

1. Open "Build Variants" panel
2. Select variant from dropdown
3. Build → Build APK

For detailed documentation, see: **BUILD_VARIANTS_GUIDE.md**
