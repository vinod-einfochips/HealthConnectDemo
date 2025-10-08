# Testing with Product Flavors

## Overview
With product flavors (dev, qa, prod), test tasks now include the flavor name.

## Test Task Names

### Before (No Flavors)
```bash
./gradlew testDebugUnitTest
```

### After (With Flavors)
```bash
./gradlew testDevDebugUnitTest
./gradlew testQaDebugUnitTest
./gradlew testProdDebugUnitTest
```

## Running Tests

### Option 1: Using Scripts (Recommended)

#### Run Tests for Dev (Default)
```bash
./run_tests.sh
```

#### Run Tests for Specific Flavor
```bash
./run_tests.sh dev   # Development
./run_tests.sh qa    # QA
./run_tests.sh prod  # Production
```

#### Run Tests for All Flavors
```bash
./run_all_tests.sh
```

### Option 2: Using Gradle

#### Run Tests for Specific Flavor
```bash
# Dev flavor
./gradlew testDevDebugUnitTest

# QA flavor
./gradlew testQaDebugUnitTest

# Prod flavor
./gradlew testProdDebugUnitTest
```

#### Run with Detailed Output
```bash
./gradlew testDevDebugUnitTest --info
```

#### Run Specific Test Class
```bash
./gradlew testDevDebugUnitTest --tests "MainViewModelTest"
```

#### Run Specific Test Method
```bash
./gradlew testDevDebugUnitTest --tests "MainViewModelTest.should validate temperature within valid range"
```

### Option 3: Android Studio

1. Open "Build Variants" panel
2. Select variant (e.g., "devDebug")
3. Right-click test file → Run tests

## Test Report Locations

### Dev Flavor
```
app/build/reports/tests/testDevDebugUnitTest/index.html
```

### QA Flavor
```
app/build/reports/tests/testQaDebugUnitTest/index.html
```

### Prod Flavor
```
app/build/reports/tests/testProdDebugUnitTest/index.html
```

## Available Test Tasks

### List All Test Tasks
```bash
./gradlew tasks --all | grep test
```

### Common Test Tasks
```bash
# Unit Tests
testDevDebugUnitTest
testDevReleaseUnitTest
testQaDebugUnitTest
testQaReleaseUnitTest
testProdDebugUnitTest
testProdReleaseUnitTest

# All Tests (includes instrumented)
testDevDebug
testQaDebug
testProdDebug
```

## Scripts Updated

### run_tests.sh
- Now accepts flavor parameter
- Defaults to "dev" if not specified
- Usage: `./run_tests.sh [flavor]`

### run_all_tests.sh (New)
- Runs tests for all flavors
- Opens all test reports
- Usage: `./run_all_tests.sh`

## Quick Commands

```bash
# Run dev tests (default)
./run_tests.sh

# Run qa tests
./run_tests.sh qa

# Run prod tests
./run_tests.sh prod

# Run all flavor tests
./run_all_tests.sh

# Run specific flavor with Gradle
./gradlew testDevDebugUnitTest
./gradlew testQaDebugUnitTest
./gradlew testProdDebugUnitTest
```

## CI/CD Integration

### GitHub Actions Example
```yaml
name: Run Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    strategy:
      matrix:
        flavor: [Dev, Qa, Prod]
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 17
        uses: actions/setup-java@v2
        with:
          java-version: '17'
      - name: Run ${{ matrix.flavor }} Tests
        run: ./gradlew test${{ matrix.flavor }}DebugUnitTest
      - name: Upload Test Report
        if: always()
        uses: actions/upload-artifact@v2
        with:
          name: test-report-${{ matrix.flavor }}
          path: app/build/reports/tests/test${{ matrix.flavor }}DebugUnitTest/
```

## Troubleshooting

### Error: Task 'testDebugUnitTest' is ambiguous
**Problem:** Old task name doesn't work with flavors

**Solution:** Use flavor-specific task:
```bash
# Instead of:
./gradlew testDebugUnitTest

# Use:
./gradlew testDevDebugUnitTest
```

### Tests Not Running
```bash
# Clean and rebuild
./gradlew clean build

# Sync Gradle
./gradlew --refresh-dependencies
```

### Wrong Flavor Tests Running
1. Check Build Variants panel in Android Studio
2. Ensure correct flavor is selected
3. Clean project: `./gradlew clean`

## Summary

✅ **Scripts Updated** - Support flavor parameter
✅ **New Script** - run_all_tests.sh for all flavors
✅ **Task Names** - Include flavor (testDevDebugUnitTest)
✅ **Documentation** - Updated guides
✅ **CI/CD Ready** - Matrix strategy examples

Use `./run_tests.sh [flavor]` to run tests for any flavor!
