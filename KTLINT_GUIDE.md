# Ktlint Integration Guide

## Overview
Ktlint is integrated into the project to enforce Kotlin code style and formatting standards with automatic report generation.

## What is Ktlint?

Ktlint is an anti-bikeshedding Kotlin linter with built-in formatter. It:
- ✅ Enforces Kotlin coding conventions
- ✅ Catches code style issues
- ✅ Provides automatic formatting
- ✅ Generates detailed reports
- ✅ Integrates with CI/CD

## Installation Complete

### Files Added/Modified

#### 1. **build.gradle** (Root)
```gradle
plugins {
    id 'org.jlleitschuh.gradle.ktlint' version '12.1.0' apply false
}
```

#### 2. **app/build.gradle**
```gradle
plugins {
    id 'org.jlleitschuh.gradle.ktlint'
}

ktlint {
    version = "1.0.1"
    android = true
    ignoreFailures = false
    reporters {
        reporter "plain"
        reporter "checkstyle"
        reporter "html"
        reporter "json"
    }
    filter {
        exclude("**/generated/**")
        exclude("**/build/**")
    }
    outputToConsole = true
    outputColorName = "RED"
}
```

#### 3. **.editorconfig**
Configuration file for code style rules

#### 4. **run_ktlint.sh**
Executable script to run ktlint and open reports

## Running Ktlint

### Option 1: Using Script (Recommended)
```bash
./run_ktlint.sh
```
✅ Runs ktlint check
✅ Generates all reports
✅ Automatically opens HTML report

### Option 2: Using Gradle Commands

#### Check Code Style
```bash
./gradlew ktlintCheck
```

#### Auto-Format Code
```bash
./gradlew ktlintFormat
```

#### Check Specific Source Set
```bash
./gradlew ktlintMainSourceSetCheck
./gradlew ktlintTestSourceSetCheck
```

### Option 3: Android Studio
1. Open Gradle panel
2. Navigate to: `app → Tasks → formatting → ktlintCheck`
3. Double-click to run

## Generated Reports

### Report Locations

#### HTML Report (Visual)
```
app/build/reports/ktlint/ktlintMainSourceSetCheck/ktlintMainSourceSetCheck.html
```
- Visual, browser-friendly
- Color-coded issues
- Easy to navigate

#### Checkstyle XML Report
```
app/build/reports/ktlint/ktlintMainSourceSetCheck.xml
```
- For CI/CD integration
- Compatible with Jenkins, SonarQube
- Machine-readable

#### JSON Report
```
app/build/reports/ktlint/ktlintMainSourceSetCheck.json
```
- Structured data format
- For custom tooling
- API integration

#### Plain Text Report
```
Console output during build
```
- Quick overview
- Terminal-friendly

## Ktlint Rules

### Enabled Rules

#### Standard Rules
- ✅ **No wildcard imports** (disabled for convenience)
- ✅ **Max line length**: 120 characters
- ✅ **Indentation**: 4 spaces
- ✅ **No trailing whitespace**
- ✅ **Final newline required**
- ✅ **Import ordering**
- ✅ **No unused imports**
- ✅ **Consistent spacing**
- ✅ **Proper bracket placement**

#### Android-Specific Rules
- ✅ Android naming conventions
- ✅ XML formatting
- ✅ Resource naming

### Disabled Rules
- ❌ **Filename rule** - Allows flexible naming
- ❌ **Function naming** - Allows backtick test names
- ❌ **Property naming** - More flexibility

## Auto-Formatting

### Format All Code
```bash
./gradlew ktlintFormat
```

### Format Specific Files
```bash
./gradlew ktlintFormat --include="**/MainActivity.kt"
```

### Before Committing
```bash
# Check for issues
./gradlew ktlintCheck

# Auto-fix issues
./gradlew ktlintFormat

# Verify fixes
./gradlew ktlintCheck
```

## Common Issues and Fixes

### Issue 1: Import Ordering
**Problem:**
```kotlin
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent  // Wrong order
```

**Fix:**
```bash
./gradlew ktlintFormat
```

**Result:**
```kotlin
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
```

### Issue 2: Line Too Long
**Problem:**
```kotlin
val message = "This is a very long message that exceeds the maximum line length of 120 characters and should be split"
```

**Fix:**
```kotlin
val message = "This is a very long message that exceeds the maximum " +
    "line length of 120 characters and should be split"
```

### Issue 3: Trailing Whitespace
**Problem:**
```kotlin
val name = "John"    // Extra spaces here
```

**Fix:**
```bash
./gradlew ktlintFormat
```

### Issue 4: Missing Final Newline
**Problem:**
File ends without newline

**Fix:**
```bash
./gradlew ktlintFormat
```

## CI/CD Integration

### GitHub Actions Example
```yaml
name: Ktlint Check

on: [push, pull_request]

jobs:
  ktlint:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 17
        uses: actions/setup-java@v2
        with:
          java-version: '17'
      - name: Run ktlint
        run: ./gradlew ktlintCheck
      - name: Upload ktlint report
        if: failure()
        uses: actions/upload-artifact@v2
        with:
          name: ktlint-report
          path: app/build/reports/ktlint/
```

### Pre-commit Hook
Create `.git/hooks/pre-commit`:
```bash
#!/bin/bash
./gradlew ktlintCheck
if [ $? -ne 0 ]; then
    echo "Ktlint check failed. Run './gradlew ktlintFormat' to fix."
    exit 1
fi
```

Make it executable:
```bash
chmod +x .git/hooks/pre-commit
```

## IDE Integration

### Android Studio / IntelliJ IDEA

#### Install Ktlint Plugin
1. Go to: `Settings → Plugins`
2. Search for "ktlint"
3. Install and restart

#### Configure Ktlint
1. Go to: `Settings → Editor → Code Style → Kotlin`
2. Click "Set from..." → "Predefined Style" → "Kotlin style guide"
3. Apply

#### Format on Save
1. Go to: `Settings → Tools → Actions on Save`
2. Enable "Reformat code"
3. Enable "Optimize imports"

## Gradle Tasks

### Available Tasks
```bash
# Check all source sets
./gradlew ktlintCheck

# Format all source sets
./gradlew ktlintFormat

# Check main source set only
./gradlew ktlintMainSourceSetCheck

# Format main source set only
./gradlew ktlintMainSourceSetFormat

# Check test source set only
./gradlew ktlintTestSourceSetCheck

# Format test source set only
./gradlew ktlintTestSourceSetFormat
```

## Report Examples

### HTML Report Features
- 📊 Summary statistics
- 📝 File-by-file breakdown
- 🔍 Line-by-line issues
- 🎨 Syntax highlighting
- 📍 Exact error locations
- 💡 Fix suggestions

### Sample Report Output
```
Ktlint Report
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Files Checked:     45
Issues Found:      12
Errors:            8
Warnings:          4
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

MainActivity.kt
  Line 25: Unused import
  Line 47: Line too long (125 > 120)
  Line 89: Missing final newline

HealthConnectManager.kt
  Line 12: Import ordering
  Line 34: Trailing whitespace
```

## Configuration Options

### Customize Rules in build.gradle
```gradle
ktlint {
    // Ktlint version
    version = "1.0.1"
    
    // Enable Android rules
    android = true
    
    // Fail build on errors
    ignoreFailures = false
    
    // Report formats
    reporters {
        reporter "plain"
        reporter "checkstyle"
        reporter "html"
        reporter "json"
    }
    
    // Exclude patterns
    filter {
        exclude("**/generated/**")
        exclude("**/build/**")
        exclude("**/test/**")  // Exclude tests if needed
    }
    
    // Console output
    outputToConsole = true
    outputColorName = "RED"
    
    // Disable specific rules
    disabledRules = [
        "no-wildcard-imports",
        "filename"
    ]
}
```

### Customize Rules in .editorconfig
```ini
[*.{kt,kts}]
max_line_length = 140  # Increase max line length
ktlint_standard_no-wildcard-imports = disabled
ktlint_standard_import-ordering = enabled
```

## Best Practices

### 1. Run Before Committing
```bash
./gradlew ktlintCheck && git commit
```

### 2. Auto-Format Regularly
```bash
./gradlew ktlintFormat
```

### 3. Fix Issues Incrementally
Don't try to fix all issues at once. Fix file by file.

### 4. Use IDE Formatting
Configure IDE to match ktlint rules for consistency.

### 5. Review Reports
Check HTML reports to understand issues better.

## Troubleshooting

### Ktlint Not Running
```bash
# Sync Gradle
./gradlew --refresh-dependencies

# Clean and rebuild
./gradlew clean ktlintCheck
```

### Reports Not Generating
```bash
# Check ktlint version
./gradlew dependencies | grep ktlint

# Verify configuration
cat app/build.gradle | grep -A 20 "ktlint {"
```

### Too Many Errors
```bash
# Set ignoreFailures temporarily
ktlint {
    ignoreFailures = true
}

# Fix gradually
./gradlew ktlintFormat
```

## Quick Commands Reference

```bash
# Check code style
./run_ktlint.sh

# Auto-format code
./gradlew ktlintFormat

# Check only
./gradlew ktlintCheck

# View reports
open app/build/reports/ktlint/ktlintMainSourceSetCheck/ktlintMainSourceSetCheck.html

# Clean reports
rm -rf app/build/reports/ktlint
```

## Summary

✅ **Ktlint Installed** - Version 1.0.1
✅ **Multiple Reporters** - HTML, XML, JSON, Plain
✅ **Android Rules** - Enabled
✅ **Auto-Formatting** - Available
✅ **Report Generation** - Configured
✅ **Script Created** - `run_ktlint.sh`
✅ **EditorConfig** - Added
✅ **CI/CD Ready** - Integration examples provided

Run ktlint now:
```bash
./run_ktlint.sh
```
