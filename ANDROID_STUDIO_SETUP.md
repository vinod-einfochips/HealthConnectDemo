# Android Studio Setup Guide

## Quick Fix for KAPT jvmTarget Error

If you're seeing this error in Android Studio:
```
Error while evaluating property 'compilerOptions.jvmTarget' of task ':app:kaptGenerateStubsDebugKotlin'
Unknown Kotlin JVM target: 21
```

### Solution 1: Change Gradle JDK to 17 (Recommended)

1. Open Android Studio
2. Go to: **File → Settings** (or **Android Studio → Preferences** on Mac)
3. Navigate to: **Build, Execution, Deployment → Build Tools → Gradle**
4. Find "Gradle JDK" dropdown
5. Select **JDK 17** (or download if not available)
6. Click **Apply** and **OK**
7. Sync Gradle: **File → Sync Project with Gradle Files**

### Solution 2: Use Embedded JDK

1. In the same Gradle settings screen
2. Select "Gradle JDK" → **Embedded JDK (jbr-17)**
3. Click **Apply** and **OK**
4. Sync Gradle

### Solution 3: Update Kotlin Version (If you want to use JDK 21)

If you prefer to use JDK 21, update the Kotlin version to 2.0+:

In `build.gradle` (root):
```gradle
plugins {
    id 'org.jetbrains.kotlin.android' version '2.0.0' apply false
}
```

## Verify Your Setup

After making changes:

1. **Clean Project**: Build → Clean Project
2. **Rebuild**: Build → Rebuild Project
3. **Run**: Click the Run button (green play icon)

## Check Current JDK

To see which JDK Android Studio is using:

1. **Help → About** (or **Android Studio → About** on Mac)
2. Look for "Runtime version"
3. Or check in **File → Project Structure → SDK Location**

## Download JDK 17

If JDK 17 is not available:

1. **File → Project Structure**
2. **SDK Location** tab
3. Click **Download JDK**
4. Select **Version: 17**
5. Choose vendor (e.g., Oracle OpenJDK, Amazon Corretto)
6. Click **Download**

## Alternative: Command Line Build

If Android Studio continues to have issues, you can build from terminal:

```bash
cd /Users/vinod.tak/Documents/Projects/CascadeProjects/windsurf-project
./gradlew clean assembleDebug
```

This uses the Gradle wrapper which is configured correctly.

## Gradle Daemon Issues

If you're still having problems, stop all Gradle daemons:

```bash
./gradlew --stop
```

Then rebuild in Android Studio.

## Project-Specific Settings

This project is configured for:
- **JVM Target**: 17
- **Kotlin**: 1.9.22
- **Gradle**: 8.12
- **Android Gradle Plugin**: 8.2.2

These settings are in:
- `app/build.gradle` (kotlinOptions.jvmTarget)
- `build.gradle` (plugin versions)
- `gradle.properties` (Gradle settings)

## Still Having Issues?

1. **Invalidate Caches**: File → Invalidate Caches → Invalidate and Restart
2. **Delete .gradle folder**: Close Android Studio, delete `.gradle` and `.idea` folders, reopen
3. **Reimport Project**: File → Close Project, then reopen

## Success Indicators

You'll know it's working when:
- ✅ Gradle sync completes without errors
- ✅ No red underlines in code files
- ✅ Build succeeds (green checkmark in Build tab)
- ✅ App runs on emulator/device

---

**Quick Command Reference**

```bash
# Check Gradle version
./gradlew --version

# Clean build
./gradlew clean

# Build debug
./gradlew assembleDebug

# Install on device
./gradlew installDebug

# Stop Gradle daemon
./gradlew --stop
```
