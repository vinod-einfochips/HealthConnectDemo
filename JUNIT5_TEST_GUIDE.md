# JUnit 5 Test Guide

## Overview
Comprehensive test suite using JUnit 5 (Jupiter) with positive, negative, and edge case tests for temperature functionality.

## Test Framework Upgrade

### From JUnit 4 to JUnit 5
- ✅ **JUnit Jupiter API** 5.10.1
- ✅ **Parameterized Tests** support
- ✅ **Nested Tests** for better organization
- ✅ **Display Names** for readable test reports
- ✅ **AssertJ** for fluent assertions
- ✅ **Mockito Jupiter** extension

## Test Structure

### Total Tests: 50+

#### 1. Positive Test Cases (20+ tests)
- ✅ Valid temperature validation
- ✅ Successful temperature recording
- ✅ Permission granting
- ✅ Temperature history loading
- ✅ User info recording

#### 2. Negative Test Cases (20+ tests)
- ✅ Invalid input validation
- ✅ Out of range temperatures
- ✅ Permission denial
- ✅ Exception handling
- ✅ Network errors

#### 3. Edge Case Tests (10+ tests)
- ✅ Boundary values
- ✅ Decimal precision
- ✅ Special characters
- ✅ Empty/null inputs

## Test Organization

```
MainViewModelTest
├── Positive Tests
│   ├── PositiveValidationTests
│   ├── PositiveRecordingTests
│   └── PositivePermissionTests
├── Negative Tests
│   ├── NegativeValidationTests
│   ├── NegativeRecordingTests
│   └── NegativePermissionTests
└── Edge Case Tests
    └── EdgeCaseTests
```

## Running Tests

### Option 1: Using Script (Recommended)
```bash
chmod +x run_tests.sh
./run_tests.sh
```

### Option 2: Using Gradle
```bash
# Run all tests
./gradlew testDebugUnitTest

# Run with detailed output
./gradlew testDebugUnitTest --info

# Run specific test class
./gradlew testDebugUnitTest --tests "MainViewModelTest"

# Run specific test method
./gradlew testDebugUnitTest --tests "MainViewModelTest.should validate temperature within valid range"
```

### Option 3: Android Studio
1. Right-click on test file
2. Select "Run 'MainViewModelTest'"
3. View results in Run panel

## HTML Test Report

### Location
```
app/build/reports/tests/testDebugUnitTest/index.html
```

### Report Contents
- ✅ Test summary (passed/failed/skipped)
- ✅ Execution time
- ✅ Test hierarchy
- ✅ Failure details with stack traces
- ✅ Standard output/error logs

### Opening Report
```bash
# macOS
open app/build/reports/tests/testDebugUnitTest/index.html

# Linux
xdg-open app/build/reports/tests/testDebugUnitTest/index.html

# Windows
start app/build/reports/tests/testDebugUnitTest/index.html
```

## Test Cases Breakdown

### Positive Validation Tests

#### 1. Valid Temperature Range
```kotlin
@ParameterizedTest
@ValueSource(strings = ["20.0", "25.5", "30.0", "36.5", "37.0", "40.0", "45.0"])
fun `should validate temperature within valid range`(temperature: String)
```
**Tests:** 7 temperatures
**Expected:** All pass validation

#### 2. Boundary Values
```kotlin
@Test
fun `should validate minimum boundary temperature`()
@Test
fun `should validate maximum boundary temperature`()
```
**Tests:** 20°C and 45°C
**Expected:** Both valid

#### 3. Normal Body Temperature
```kotlin
@Test
fun `should validate normal body temperature`()
```
**Tests:** 36.5°C, 37.0°C, 37.5°C
**Expected:** All valid

#### 4. Decimal Precision
```kotlin
@Test
fun `should validate temperature with decimal precision`()
```
**Tests:** 36.1, 36.25, 36.789
**Expected:** All valid

### Positive Recording Tests

#### 1. Successful Recording
```kotlin
@Test
fun `should successfully record valid temperature`()
```
**Tests:** Recording 37.5°C
**Expected:** Success message

#### 2. Recording with User Info
```kotlin
@Test
fun `should successfully record temperature with user info`()
```
**Tests:** Recording with UserInfo
**Expected:** Success with user details

#### 3. Boundary Recording
```kotlin
@ParameterizedTest
@ValueSource(doubles = [20.0, 45.0])
fun `should record boundary temperatures`(temperature: Double)
```
**Tests:** 20°C and 45°C
**Expected:** Both recorded successfully

#### 4. Multiple Recordings
```kotlin
@Test
fun `should record multiple temperatures in sequence`()
```
**Tests:** 4 sequential recordings
**Expected:** All successful

### Negative Validation Tests

#### 1. Invalid Input
```kotlin
@ParameterizedTest
@ValueSource(strings = ["", "abc", "36.5.5", "not a number", "temp", "##", "null"])
fun `should reject invalid temperature input`(invalidInput: String)
```
**Tests:** 7 invalid inputs
**Expected:** All rejected

#### 2. Below Minimum
```kotlin
@ParameterizedTest
@ValueSource(strings = ["19.9", "15.0", "10.0", "0.0", "-5.0"])
fun `should reject temperature below minimum`(temperature: String)
```
**Tests:** 5 temperatures below 20°C
**Expected:** All rejected

#### 3. Above Maximum
```kotlin
@ParameterizedTest
@ValueSource(strings = ["45.1", "50.0", "60.0", "100.0"])
fun `should reject temperature above maximum`(temperature: String)
```
**Tests:** 4 temperatures above 45°C
**Expected:** All rejected

#### 4. Special Characters
```kotlin
@Test
fun `should reject special characters`()
```
**Tests:** 36@5, 37#0, 38$5, 39%0
**Expected:** All rejected

### Negative Recording Tests

#### 1. Below Minimum Recording
```kotlin
@ParameterizedTest
@ValueSource(doubles = [19.9, 15.0, 10.0, 0.0])
fun `should fail to record temperature below minimum`(temperature: Double)
```
**Tests:** 4 temperatures
**Expected:** All fail with error message

#### 2. Above Maximum Recording
```kotlin
@ParameterizedTest
@ValueSource(doubles = [45.1, 50.0, 60.0, 100.0])
fun `should fail to record temperature above maximum`(temperature: Double)
```
**Tests:** 4 temperatures
**Expected:** All fail with error message

#### 3. Exception Handling
```kotlin
@Test
fun `should handle exception from HealthConnectManager`()
```
**Tests:** RuntimeException scenario
**Expected:** Graceful error handling

#### 4. Network Timeout
```kotlin
@Test
fun `should handle network timeout exception`()
```
**Tests:** Network timeout scenario
**Expected:** Error captured

### Edge Case Tests

#### 1. Exact Boundaries
```kotlin
@Test
fun `should handle exactly minimum temperature`()
@Test
fun `should handle exactly maximum temperature`()
```
**Tests:** Exactly 20.0°C and 45.0°C
**Expected:** Both valid

#### 2. Near Boundaries
```kotlin
@Test
fun `should handle temperature just below minimum`()
@Test
fun `should handle temperature just above maximum`()
```
**Tests:** 19.999999 and 45.000001
**Expected:** Both invalid

#### 3. High Precision
```kotlin
@Test
fun `should handle very long decimal precision`()
```
**Tests:** 37.123456789012345
**Expected:** Valid

## Test Report Example

### Summary Section
```
Tests: 52
Passed: 50
Failed: 2
Skipped: 0
Duration: 2.5s
Success Rate: 96%
```

### Test Hierarchy
```
MainViewModelTest
├── ✓ Positive Temperature Validation Tests (7 tests)
├── ✓ Positive Temperature Recording Tests (4 tests)
├── ✓ Positive Permission Tests (2 tests)
├── ✓ Negative Temperature Validation Tests (15 tests)
├── ✓ Negative Temperature Recording Tests (8 tests)
├── ✓ Negative Permission Tests (4 tests)
└── ✓ Edge Case Tests (6 tests)
```

## Continuous Integration

### GitHub Actions Example
```yaml
name: Run Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 17
        uses: actions/setup-java@v2
        with:
          java-version: '17'
      - name: Run tests
        run: ./gradlew testDebugUnitTest
      - name: Upload test report
        uses: actions/upload-artifact@v2
        with:
          name: test-report
          path: app/build/reports/tests/testDebugUnitTest/
```

## Best Practices

### 1. Test Naming
- ✅ Use descriptive names with backticks
- ✅ Follow pattern: `should [expected behavior] when [condition]`
- ✅ Use @DisplayName for clarity

### 2. Test Organization
- ✅ Group related tests with @Nested
- ✅ Separate positive, negative, and edge cases
- ✅ Use parameterized tests for multiple inputs

### 3. Assertions
- ✅ Use specific assertions (assertEquals, assertTrue, etc.)
- ✅ Include meaningful failure messages
- ✅ Test both success and failure paths

### 4. Mocking
- ✅ Mock external dependencies
- ✅ Verify interactions with mocks
- ✅ Use realistic test data

## Troubleshooting

### Tests Not Running
```bash
# Clean and rebuild
./gradlew clean build

# Sync Gradle
./gradlew --refresh-dependencies
```

### Report Not Generated
```bash
# Ensure tests ran
./gradlew testDebugUnitTest --info

# Check build directory
ls -la app/build/reports/tests/
```

### Compilation Errors
```bash
# Invalidate caches (Android Studio)
File → Invalidate Caches / Restart

# Or use Gradle
./gradlew clean build
```

## Coverage Report (Optional)

### Add JaCoCo Plugin
```gradle
plugins {
    id 'jacoco'
}

jacoco {
    toolVersion = "0.8.10"
}

tasks.withType(Test) {
    finalizedBy jacocoTestReport
}

jacocoTestReport {
    reports {
        html.required = true
        xml.required = true
    }
}
```

### Generate Coverage
```bash
./gradlew testDebugUnitTest jacocoTestReport
open app/build/reports/jacoco/testDebugUnitTest/html/index.html
```

## Summary

✅ **50+ comprehensive tests**
✅ **JUnit 5 with modern features**
✅ **Positive, negative, and edge cases**
✅ **HTML test reports**
✅ **Parameterized tests**
✅ **Nested test organization**
✅ **Clear test names and documentation**
✅ **Easy to run and maintain**

## Next Steps

1. Run tests: `./run_tests.sh`
2. View HTML report
3. Add more tests as needed
4. Integrate with CI/CD
5. Monitor test coverage
